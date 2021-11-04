https://github.com/vlad101vlad/UBB-ComputerScience-3rdYear-1st-Semester/tree/main/Formal%20Languages%20and%20Compiler%20Design/Labs/Lab4/src/com/company

DOCUMENTATION(work in progress):

CLASS DIAGRAM:

**StringOperationUtils** 
-----------------------------
+removeSpaces(tokenCandidates: List<String>): List<String>
    
    Given a list of token candidates extracted from the source code, it elimitenates
    the " "(blank) spaces from it.

+removeDuplicates(stringWithDuplicates: String): String
    
    Given a string with duplicate characters, it returns a string with unique characters.
    It is used for using it in the regex for generating the tokens from the source code
    
    
**FileOperationsUtils**
-----------------------------
+readTokens(): List<String> (throws FileNotFoundException) 
    
    It reads the source code from a file and it generates some partial tokens in their 
    raw form (just splitting by blank spaces and new lines)
    
+readApplication(): String (throws FileNotFoundException)

    It reads the source code from the file and it adds it to a string
    
+writeToFile(fileName: String,toBeWritten: Object): void (throws IOException)

    Writes to a file the object. Used for writing the PIF and the SymbolTable
    
**PIF**
-----------------------------
-table: List<Pair<String, Integer>>; 

methods:

+addToPIF(token: String,index: Integer): void

    It adds a token to the PIF(table) with their corresponding index in the SymbolTable
+toString(): String
    
    Pretty print method for writing in the file
    
**SymbolTable**
-----------------------------


    The symbol table is represented by the clas SymbolTable.
    
    The internal structure of the symbol table is as follows:
    
    integer -> List<String,String>
    
    The main logic, for each new element we want to add to the symbol table:
    
    * we will pass it to the hash function
    * we get the List which coresponds to the result of the hashValue
    * we search for the element toBeAdded in this List:
      * we find it, we return the current position
      * we don't find it , we add it to this list and return the new size of the list
    
    In order to see the diference between <identifier> and <constant>, each element
    of the hash table will be a pair of the format <value, type>


-------------------------------------------------------------------------------------------------
Class diagram:

- symbolTableLength: int
- symbolTable: Map< Integer, List< Map.Entry <String, String>>
___________________________________

+hashFunction(String key): int

+addToTable(String type, String toBeAdded): int


**TokenChecker**
-----------------------------


