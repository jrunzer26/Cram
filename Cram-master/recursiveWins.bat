SET teamname=recursiveWins
javac -cp Cram.jar algorithms/%teamname%.java
java -cp Cram.jar;. algorithms.%teamname%
pause
