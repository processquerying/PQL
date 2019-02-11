// Generated from PQL.g4 by ANTLR 4.1
package org.pql.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PQLParser}.
 */
public interface PQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PQLParser#setOfAllTasks}.
	 * @param ctx the parse tree
	 */
	void enterSetOfAllTasks(@NotNull PQLParser.SetOfAllTasksContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setOfAllTasks}.
	 * @param ctx the parse tree
	 */
	void exitSetOfAllTasks(@NotNull PQLParser.SetOfAllTasksContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(@NotNull PQLParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(@NotNull PQLParser.NegationContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setComparison}.
	 * @param ctx the parse tree
	 */
	void enterSetComparison(@NotNull PQLParser.SetComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setComparison}.
	 * @param ctx the parse tree
	 */
	void exitSetComparison(@NotNull PQLParser.SetComparisonContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryTracePredicate}.
	 * @param ctx the parse tree
	 */
	void enterUnaryTracePredicate(@NotNull PQLParser.UnaryTracePredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryTracePredicate}.
	 * @param ctx the parse tree
	 */
	void exitUnaryTracePredicate(@NotNull PQLParser.UnaryTracePredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#subsetOf}.
	 * @param ctx the parse tree
	 */
	void enterSubsetOf(@NotNull PQLParser.SubsetOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#subsetOf}.
	 * @param ctx the parse tree
	 */
	void exitSubsetOf(@NotNull PQLParser.SubsetOfContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#approximate}.
	 * @param ctx the parse tree
	 */
	void enterApproximate(@NotNull PQLParser.ApproximateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#approximate}.
	 * @param ctx the parse tree
	 */
	void exitApproximate(@NotNull PQLParser.ApproximateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicateName(@NotNull PQLParser.UnaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicateName(@NotNull PQLParser.UnaryPredicateNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setOfTasksLiteral}.
	 * @param ctx the parse tree
	 */
	void enterSetOfTasksLiteral(@NotNull PQLParser.SetOfTasksLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setOfTasksLiteral}.
	 * @param ctx the parse tree
	 */
	void exitSetOfTasksLiteral(@NotNull PQLParser.SetOfTasksLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicate(@NotNull PQLParser.BinaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicate(@NotNull PQLParser.BinaryPredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#identical}.
	 * @param ctx the parse tree
	 */
	void enterIdentical(@NotNull PQLParser.IdenticalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#identical}.
	 * @param ctx the parse tree
	 */
	void exitIdentical(@NotNull PQLParser.IdenticalContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull PQLParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull PQLParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void enterSetPredicate(@NotNull PQLParser.SetPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void exitSetPredicate(@NotNull PQLParser.SetPredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#taskInSetOfTasks}.
	 * @param ctx the parse tree
	 */
	void enterTaskInSetOfTasks(@NotNull PQLParser.TaskInSetOfTasksContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#taskInSetOfTasks}.
	 * @param ctx the parse tree
	 */
	void exitTaskInSetOfTasks(@NotNull PQLParser.TaskInSetOfTasksContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#intersection}.
	 * @param ctx the parse tree
	 */
	void enterIntersection(@NotNull PQLParser.IntersectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#intersection}.
	 * @param ctx the parse tree
	 */
	void exitIntersection(@NotNull PQLParser.IntersectionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicateMacroSetSet}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateMacroSetSet(@NotNull PQLParser.BinaryPredicateMacroSetSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicateMacroSetSet}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateMacroSetSet(@NotNull PQLParser.BinaryPredicateMacroSetSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void enterTruthValue(@NotNull PQLParser.TruthValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void exitTruthValue(@NotNull PQLParser.TruthValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#insertQuery}.
	 * @param ctx the parse tree
	 */
	void enterInsertQuery(@NotNull PQLParser.InsertQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#insertQuery}.
	 * @param ctx the parse tree
	 */
	void exitInsertQuery(@NotNull PQLParser.InsertQueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#isNotFalse}.
	 * @param ctx the parse tree
	 */
	void enterIsNotFalse(@NotNull PQLParser.IsNotFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#isNotFalse}.
	 * @param ctx the parse tree
	 */
	void exitIsNotFalse(@NotNull PQLParser.IsNotFalseContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#different}.
	 * @param ctx the parse tree
	 */
	void enterDifferent(@NotNull PQLParser.DifferentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#different}.
	 * @param ctx the parse tree
	 */
	void exitDifferent(@NotNull PQLParser.DifferentContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#tasks}.
	 * @param ctx the parse tree
	 */
	void enterTasks(@NotNull PQLParser.TasksContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#tasks}.
	 * @param ctx the parse tree
	 */
	void exitTasks(@NotNull PQLParser.TasksContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(@NotNull PQLParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(@NotNull PQLParser.ParenthesesContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#isNotTrue}.
	 * @param ctx the parse tree
	 */
	void enterIsNotTrue(@NotNull PQLParser.IsNotTrueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#isNotTrue}.
	 * @param ctx the parse tree
	 */
	void exitIsNotTrue(@NotNull PQLParser.IsNotTrueContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull PQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull PQLParser.QueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicateMacro}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateMacro(@NotNull PQLParser.BinaryPredicateMacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicateMacro}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateMacro(@NotNull PQLParser.BinaryPredicateMacroContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#isFalse}.
	 * @param ctx the parse tree
	 */
	void enterIsFalse(@NotNull PQLParser.IsFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#isFalse}.
	 * @param ctx the parse tree
	 */
	void exitIsFalse(@NotNull PQLParser.IsFalseContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#task}.
	 * @param ctx the parse tree
	 */
	void enterTask(@NotNull PQLParser.TaskContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#task}.
	 * @param ctx the parse tree
	 */
	void exitTask(@NotNull PQLParser.TaskContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicateConstruction}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateConstruction(@NotNull PQLParser.BinaryPredicateConstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicateConstruction}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateConstruction(@NotNull PQLParser.BinaryPredicateConstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#difference}.
	 * @param ctx the parse tree
	 */
	void enterDifference(@NotNull PQLParser.DifferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#difference}.
	 * @param ctx the parse tree
	 */
	void exitDifference(@NotNull PQLParser.DifferenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#nestedQuery}.
	 * @param ctx the parse tree
	 */
	void enterNestedQuery(@NotNull PQLParser.NestedQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#nestedQuery}.
	 * @param ctx the parse tree
	 */
	void exitNestedQuery(@NotNull PQLParser.NestedQueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#anySomeEachAll}.
	 * @param ctx the parse tree
	 */
	void enterAnySomeEachAll(@NotNull PQLParser.AnySomeEachAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#anySomeEachAll}.
	 * @param ctx the parse tree
	 */
	void exitAnySomeEachAll(@NotNull PQLParser.AnySomeEachAllContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setComparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterSetComparisonOperator(@NotNull PQLParser.SetComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setComparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitSetComparisonOperator(@NotNull PQLParser.SetComparisonOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateName(@NotNull PQLParser.BinaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateName(@NotNull PQLParser.BinaryPredicateNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void enterSelectQuery(@NotNull PQLParser.SelectQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void exitSelectQuery(@NotNull PQLParser.SelectQueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(@NotNull PQLParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(@NotNull PQLParser.VarNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setOfTasksConstruction}.
	 * @param ctx the parse tree
	 */
	void enterSetOfTasksConstruction(@NotNull PQLParser.SetOfTasksConstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setOfTasksConstruction}.
	 * @param ctx the parse tree
	 */
	void exitSetOfTasksConstruction(@NotNull PQLParser.SetOfTasksConstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryPredicateMacro}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicateMacro(@NotNull PQLParser.UnaryPredicateMacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryPredicateMacro}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicateMacro(@NotNull PQLParser.UnaryPredicateMacroContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#overlapsWith}.
	 * @param ctx the parse tree
	 */
	void enterOverlapsWith(@NotNull PQLParser.OverlapsWithContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#overlapsWith}.
	 * @param ctx the parse tree
	 */
	void exitOverlapsWith(@NotNull PQLParser.OverlapsWithContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#trace}.
	 * @param ctx the parse tree
	 */
	void enterTrace(@NotNull PQLParser.TraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#trace}.
	 * @param ctx the parse tree
	 */
	void exitTrace(@NotNull PQLParser.TraceContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(@NotNull PQLParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(@NotNull PQLParser.ConjunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#similarity}.
	 * @param ctx the parse tree
	 */
	void enterSimilarity(@NotNull PQLParser.SimilarityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#similarity}.
	 * @param ctx the parse tree
	 */
	void exitSimilarity(@NotNull PQLParser.SimilarityContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void enterProposition(@NotNull PQLParser.PropositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void exitProposition(@NotNull PQLParser.PropositionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#anyAll}.
	 * @param ctx the parse tree
	 */
	void enterAnyAll(@NotNull PQLParser.AnyAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#anyAll}.
	 * @param ctx the parse tree
	 */
	void exitAnyAll(@NotNull PQLParser.AnyAllContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void enterAttributeName(@NotNull PQLParser.AttributeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#attributeName}.
	 * @param ctx the parse tree
	 */
	void exitAttributeName(@NotNull PQLParser.AttributeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull PQLParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull PQLParser.AttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setOfTasks}.
	 * @param ctx the parse tree
	 */
	void enterSetOfTasks(@NotNull PQLParser.SetOfTasksContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setOfTasks}.
	 * @param ctx the parse tree
	 */
	void exitSetOfTasks(@NotNull PQLParser.SetOfTasksContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#logicalTest}.
	 * @param ctx the parse tree
	 */
	void enterLogicalTest(@NotNull PQLParser.LogicalTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#logicalTest}.
	 * @param ctx the parse tree
	 */
	void exitLogicalTest(@NotNull PQLParser.LogicalTestContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(@NotNull PQLParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(@NotNull PQLParser.EventContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#variables}.
	 * @param ctx the parse tree
	 */
	void enterVariables(@NotNull PQLParser.VariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#variables}.
	 * @param ctx the parse tree
	 */
	void exitVariables(@NotNull PQLParser.VariablesContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(@NotNull PQLParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(@NotNull PQLParser.DisjunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicate(@NotNull PQLParser.UnaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicate(@NotNull PQLParser.UnaryPredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(@NotNull PQLParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(@NotNull PQLParser.LabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(@NotNull PQLParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(@NotNull PQLParser.UnionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#setOfTasksParentheses}.
	 * @param ctx the parse tree
	 */
	void enterSetOfTasksParentheses(@NotNull PQLParser.SetOfTasksParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#setOfTasksParentheses}.
	 * @param ctx the parse tree
	 */
	void exitSetOfTasksParentheses(@NotNull PQLParser.SetOfTasksParenthesesContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#properSubsetOf}.
	 * @param ctx the parse tree
	 */
	void enterProperSubsetOf(@NotNull PQLParser.ProperSubsetOfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#properSubsetOf}.
	 * @param ctx the parse tree
	 */
	void exitProperSubsetOf(@NotNull PQLParser.ProperSubsetOfContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#isTrue}.
	 * @param ctx the parse tree
	 */
	void enterIsTrue(@NotNull PQLParser.IsTrueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#isTrue}.
	 * @param ctx the parse tree
	 */
	void exitIsTrue(@NotNull PQLParser.IsTrueContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#universe}.
	 * @param ctx the parse tree
	 */
	void enterUniverse(@NotNull PQLParser.UniverseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#universe}.
	 * @param ctx the parse tree
	 */
	void exitUniverse(@NotNull PQLParser.UniverseContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull PQLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull PQLParser.VariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(@NotNull PQLParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(@NotNull PQLParser.AttributesContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#locations}.
	 * @param ctx the parse tree
	 */
	void enterLocations(@NotNull PQLParser.LocationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#locations}.
	 * @param ctx the parse tree
	 */
	void exitLocations(@NotNull PQLParser.LocationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull PQLParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull PQLParser.LocationContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#binaryPredicateMacroTaskSet}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateMacroTaskSet(@NotNull PQLParser.BinaryPredicateMacroTaskSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#binaryPredicateMacroTaskSet}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateMacroTaskSet(@NotNull PQLParser.BinaryPredicateMacroTaskSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#locationPath}.
	 * @param ctx the parse tree
	 */
	void enterLocationPath(@NotNull PQLParser.LocationPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#locationPath}.
	 * @param ctx the parse tree
	 */
	void exitLocationPath(@NotNull PQLParser.LocationPathContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryPredicateConstruction}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicateConstruction(@NotNull PQLParser.UnaryPredicateConstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryPredicateConstruction}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicateConstruction(@NotNull PQLParser.UnaryPredicateConstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PQLParser#unaryTracePredicateName}.
	 * @param ctx the parse tree
	 */
	void enterUnaryTracePredicateName(@NotNull PQLParser.UnaryTracePredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PQLParser#unaryTracePredicateName}.
	 * @param ctx the parse tree
	 */
	void exitUnaryTracePredicateName(@NotNull PQLParser.UnaryTracePredicateNameContext ctx);
}