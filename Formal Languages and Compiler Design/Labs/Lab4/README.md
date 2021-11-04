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
    Class is used for checking whether a given candidate token is indeed
    a token (identifier, constant, reserved word/separator/operator)
    
-tokenCandidate: String;

-tokens: List;



methods:

+checkToken(): String

    The main method of the class. It checks for the tokenCandite in which category
    it belongs: identifier, constant or token.
    It returns 
        -> identifier
        -> constant
        -> token
        -> "" (empty string for lexical error)

+checkNumber(numberCandidate: String): boolean
        
    Checks whether the argument is a nubmer or not. It uses 
    this regex: ^[-+]?[1-9]+[0-9]*$

+checkIdentifier(identifierCandidate: String): boolean
    
    Checks whether the argument is a identifier or not. It uses the 
    following regex: ^[_a-zA-Z][_a-zA-Z0-9]*$.
    Identifier must start with a letter or underscore and can contain any digits
    
    
**MAIN SERVICE**
-----------------------------

    The main class. This is the scanner in which all the magic happens.
    


-tokens: List;
    
    It contains all the tokens that have to be analyzed
   
-separators: List;

-pif: PIF;

-symbolTable: SymbolTable;

-LEXICAL_ERROR: String;



*methods:

+run(applicationText: String): void (throws IOException)

    It starts the scanner. It loops through all the tokens 
    extracted from the application text ( source code) and it tries 
    to categorize each of them in identifier,constant, token. Depending on the 
    category, they will be added to PIF and SymbolTable with the according rules 
    from the algorithm from the course.
    If they don't belong to any category, there will be a lexical error



