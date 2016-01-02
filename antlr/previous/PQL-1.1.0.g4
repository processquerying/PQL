   /*
    [The "BSD licence"]
    Copyright (c) 2014-2015 Artem Polyvyanyy (http://polyvyanyy.com/)
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:
    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.
    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
   */

   // PQL version 1.1 grammar for ANTLR v4

   grammar PQL;

   query      : selectQuery |
                insertQuery;
                
   selectQuery : variables 
              SELECT attributes 
              FROM locations 
              (WHERE predicate)? EOS ;

   insertQuery : variables 
              INSERT trace INTO locations 
              (WHERE predicate)? EOS ;              

   variables  : variable* ;
   variable   : varName ASSIGN 
                setOfTasks EOS ;
   
   varName    : VARIABLE_NAME ;

   attributes : attribute (SEP attribute)* ;
   attribute  : universe
              | attributeID
              | attributeName
              | attributeModel ;
           
   locations  : location (SEP location)* ;
   location   : universe
              | locationID
              | locationDirectory ;
              
   universe           : UNIVERSE ;
   attributeID        : ATTRIBUTE_ID ;
   attributeName      : ATTRIBUTE_NAME ;
   attributeModel     : ATTRIBUTE_MODEL ;
   locationID         : INTEGER ;
   locationDirectory  : STRING ;

   setOfTasks : tasks
              | union
              | intersection
              | difference ;

   tasks      : ( varName | universe )
              | setOfTasksLiteral
              | setOfTasksConstruction
              | setOfTasksParentheses ;
              
   setOfTasksLiteral  : 
              LB (task (SEP task)*)? RB ;
 
   trace      : 
              LTB (event (SEP event)*)? RTB ; 
   
   event 	    :	universe | task; 
   
   task       : approximate label 
              | label (LSB similarity RSB)? ; 
   approximate: TILDE ;
   label      : STRING ;
   similarity : SIMILARITY ;
   
   setOfTasksConstruction      :
                unaryPredicateConstruction
              | binaryPredicateConstruction ;

   unaryPredicateConstruction  :
                (GET_TASKS)unaryPredicateName 
                LP setOfTasks RP ;
   
   binaryPredicateConstruction :
                (GET_TASKS)binaryPredicateName 
                LP setOfTasks SEP setOfTasks 
                SEP anyAll RP ;

   anyAll     : ANY | ALL ;
   
   unaryPredicateName : CAN_OCCUR
              | ALWAYS_OCCURS;
   
   unaryTracePredicateName : EXECUTES;

   
   binaryPredicateName: CAN_CONFLICT
              | CAN_COOCCUR 
              | CONFLICT
              | COOCCUR
              | TOTAL_CAUSAL
              | TOTAL_CONCUR ;

   predicate  : proposition
              | conjunction
              | disjunction
              | logicalTest ;

   proposition: unaryPredicate
              | binaryPredicate
			  | unaryTracePredicate
              | unaryPredicateMacro
              | binaryPredicateMacro
              | setPredicate
              | truthValue
              | parentheses
              | negation ;

   unaryPredicate      : unaryPredicateName 
                LP task RP ;

   binaryPredicate     : binaryPredicateName
                LP task SEP task RP ;  

   unaryTracePredicate : 
                unaryTracePredicateName 
                LP trace RP ;				
              
   unaryPredicateMacro : unaryPredicateName
                LP setOfTasks SEP anyAll RP ;
		   
   binaryPredicateMacro: 
                binaryPredicateMacroTaskSet
              | binaryPredicateMacroSetSet ;    

   binaryPredicateMacroTaskSet :
                binaryPredicateName LP task 
                SEP setOfTasks SEP anyAll RP ;
		   
   binaryPredicateMacroSetSet  : 
              binaryPredicateName 
              LP setOfTasks SEP setOfTasks 
              SEP anyEachAll RP ;

   anyEachAll  : ANY | EACH | ALL ;
    
   setPredicate     : taskInSetOfTasks
              | setComparison ;
                     
   taskInSetOfTasks : task IN setOfTasks ;

   setComparison    : setOfTasks 
              setComparisonOperator 
              setOfTasks ;

   setComparisonOperator : identical
              | different
              | overlapsWith
              | subsetOf
              | properSubsetOf ;              

   truthValue : TRUE
              | FALSE ;

   logicalTest: isTrue
              | isNotTrue
              | isFalse
              | isNotFalse ;
              
   union      : (tasks | difference | 
                intersection) UNION (tasks | 
                difference | intersection)
                (UNION (tasks | difference 
                | intersection))* ;

   intersection : (tasks | difference) 
                  INTERSECTION 
                  (tasks | difference)
                  (INTERSECTION (tasks 
                  | difference))* ;
   
   difference   : tasks DIFFERENCE tasks
                | tasks DIFFERENCE 
                  difference ; 
 
   negation     : NOT proposition ;

   isTrue       : proposition IS TRUE ;
   isNotTrue    : proposition IS NOT TRUE ;
   isFalse      : proposition IS FALSE ;
   isNotFalse   : proposition IS NOT FALSE ;

   disjunction  : (proposition | logicalTest | 
            conjunction) OR (proposition | 
            logicalTest | conjunction) (OR 
            (proposition | logicalTest 
            | conjunction))* ;
		   
   conjunction  : (proposition | logicalTest) 
            AND (proposition | logicalTest)
            (AND (proposition 
            | logicalTest))* ;

   parentheses  : LP proposition RP 
           | LP conjunction RP 
           | LP disjunction RP 
           | LP logicalTest RP ;

   setOfTasksParentheses : LP varName RP
           | LP universe RP
           | LP setOfTasksLiteral RP
           | LP setOfTasksConstruction RP
           | LP union RP
           | LP difference RP
           | LP intersection RP
           | LP setOfTasksParentheses RP ;

   UNIVERSE         : '*' ;
   ATTRIBUTE_ID     : 'id' ;
   ATTRIBUTE_NAME   : 'name' ;
   ATTRIBUTE_MODEL  : 'model' ;
   
   STRING       : DQ ( ESC_SEQ 
                | ~('\\'|'"') )* DQ ;
   INTEGER      : '0' | '1'..'9' '0'..'9'* ;
   VARIABLE_NAME: ('a'..'z'|'_') 
                  ('a'..'z'|'0'..'9'|'_')*;
   SIMILARITY   : '1' | '0' ('.' '0'..'9'+)? 
                | '.' '0'..'9'+ ;

   LP          : '(' ;
   RP          : ')' ;
   LB          : '{' ;
   RB          : '}' ;
   LSB         : '[' ;
   RSB         : ']' ;
   LTB	       : '<' ;
   RTB	       : '>' ;
   DQ          : '"' ;
   EOS         : ';' ; 
   SEP         : ',' ;
   ASSIGN      : '=' ;
   TILDE       : '~' ;
   
   
   ESC_SEQ     : '\\' ('\"'|'\\'|'/'|'b'|
               'f'|'n'|'r'|'t') 
               | UNICODE_ESC ;
   UNICODE_ESC : '\\' 'u' HEX_DIGIT 
               HEX_DIGIT HEX_DIGIT HEX_DIGIT ;
   HEX_DIGIT   : ('0'..'9'|
               'a'..'f'|'A'..'F') ;
   WS          : [ \r\t\n]+ -> skip ;
   LINE_COMMENT: '--' ~[\r\n]* -> skip ;

   SELECT      : 'SELECT' ;
   INSERT      : 'INSERT' ;
   INTO        : 'INTO' ;
   FROM        : 'FROM' ;
   WHERE       : 'WHERE' ;
   EQUALS      : 'EQUALS' ;
   OVERLAPS    : 'OVERLAPS' ;
   WITH        : 'WITH' ;
   SUBSET      : 'SUBSET' ;
   PROPER      : 'PROPER' ;
   GET_TASKS   : 'GetTasks' ;

   NOT         : 'NOT' ;
   AND         : 'AND' ;
   OR          : 'OR' ;

   ANY         : 'ANY' ;
   EACH        : 'EACH' ;
   ALL         : 'ALL' ;

   IN          : 'IN' ;
   IS          : 'IS' ;
   OF          : 'OF' ;
   
   TRUE        : 'TRUE' ; 
   FALSE       : 'FALSE' ;

   identical       : EQUALS ;
   different       : NOT EQUALS ;
   overlapsWith    : OVERLAPS WITH ;
   subsetOf        : IS SUBSET OF ;
   properSubsetOf  : IS PROPER SUBSET OF ;

   UNION           : 'UNION' ;
   INTERSECTION    : 'INTERSECT' ;
   DIFFERENCE      : 'EXCEPT' ;

   CAN_OCCUR       : 'CanOccur' ;
   ALWAYS_OCCURS   : 'AlwaysOccurs' ;
   EXECUTES		     : 'Executes';
   CAN_CONFLICT    : 'CanConflict' ;
   CAN_COOCCUR     : 'CanCooccur' ;
   CONFLICT        : 'Conflict' ;
   COOCCUR         : 'Cooccur' ;
   TOTAL_CAUSAL    : 'TotalCausal' ;
   TOTAL_CONCUR    : 'TotalConcurrent' ;