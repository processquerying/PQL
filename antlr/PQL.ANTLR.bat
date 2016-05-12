java -classpath .;./antlr-4.1-complete.jar org.antlr.v4.Tool -package org.pql.antlr PQL.g4
copy *.java .\..\src\org\pql\antlr\

java -classpath .;./antlr-4.1-complete.jar org.antlr.v4.Tool PQL.g4
copy *.java .\..\src\

pause