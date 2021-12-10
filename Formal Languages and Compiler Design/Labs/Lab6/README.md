Descendent recursive parser:

Table output:
-
requires:
    
    grammarModel: GrammarModel
    workingStack: List<Object>
    
uses:
- fromStackToList(stack: Stack\<Object>): List\<Object>
    
        This function transforms a stack to list;

- findNextNonTerminal(startIndex: int): NonterminalAndProduction
  
       Given the working stack and an index, it returns the next nontemrinal and production 
      (in order to generate the next row in the table)
- addElement(index: int, value: String, parent: int, leftSibling:int)

            Given an index, a value, an index for the parent and the left sibling,
      the function will add the new element to the output table
- generateTableRows(nonTerminalAndProduction: NonterminalAndProduction, index: int, parentIndex: int, 
  nonterminalParentIndecies: List\<NonterminalParentIndex>):
        
          Given a non terminal, an index, a prent index, a foundFirst boolean and a list of new Nonterminals,
          the function will expand the production and will compute the next rows in the table for this nonterminal 
           with their respective indicies