https://github.com/vlad101vlad/UBB-ComputerScience-3rdYear-1st-Semester/tree/main/Formal%20Languages%20and%20Compiler%20Design/Labs/Lab3/src/en/ubbcluj/info

###################################################################
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

SymbolTable:

- symbolTableLength: int
- symbolTable: Map< Integer, List< Map.Entry <String, String>>
___________________________________

+hashFunction(String key): int

+addToTable(String type, String toBeAdded): int