https://github.com/vlad101vlad/UBB-ComputerScience-3rdYear-1st-Semester/tree/main/Formal%20Languages%20and%20Compiler%20Design/Labs/Lab4/src/com/company

DOCUMENTATION(work in progress):

The parser works in the following way:

* At the start of the program, we extract all the tokens from the tokens.in and we save them in memory.
* Also , at the start of the program, we read the whole program in the toy language and we put it in a string and save it in memory.

Then , the main algorithm for the scanner is used:

-> While we are not at the end of the file, we keep looping
  -> we detect tokens
      -> we search for white spaces(as this is our default separator) and make a substring from the two adjacent spaces 
      -> we check whether the found substring is indeed a token from our lexic
         -> if not , we throw a lexic error, 
	 -> if yes , we do the computations for it

