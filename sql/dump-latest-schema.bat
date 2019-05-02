@echo off
setlocal
SET AREYOUSURE=N
:PROMPT
SET /P AREYOUSURE=This will reset the database. Are you sure you want to continue[Y/N]?
IF /I "%AREYOUSURE%" NEQ "Y" GOTO END

@echo on

mysql --user=root --password=password --execute="call reset()" pql 

mysqldump --no-data --routines --databases --user=root --password=password pql > PQL.MySQL-latest.sql

:END
endlocal

pause