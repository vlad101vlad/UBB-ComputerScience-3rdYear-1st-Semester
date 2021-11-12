https://github.com/vlad101vlad/UBB-ComputerScience-3rdYear-1st-Semester/tree/main/Formal%20Languages%20and%20Compiler%20Design/Labs/Lab5


**DOMAIN**
____
_Transition_

-startState: String,

-nextState: String,

-literal: String,

-toSring: String (will be written as: |startState| -> |nextState|: |literal|)

_FA Model_

-initialState: String,

-finalState: List\<String\>

-transitionList: List\<Transition\>

--------------------------
+getSetOfStates(): Set\<String\>
    
    Go a given FA model, this method will return all the states in the FA, computing them 
    from the Transition list
    
+getAlphabet(): Set\<String\>

    For a given FA model, this method will return all the literals in the FA, computing them
    from the Transition list
    
-getPosibleNextState(currentTerminal: String, currentState: String): List\<String\>

    For a given terminal and a given intial state, the method will compute all the posible next states
    by traversing the transition list and finding the possible next states that accept this step
    
+isSequenceAcceptedByFa(index: String,sequence: List\<String\>, currentState:string): boolean
    
    Given a sequence and the initial state, the method will return whether the FA will accept this sequence or not
    From main, we call this method with index = 0, sequence = read from file, currentState = initialState.
    For each "currentState", the method will generate all the possible next states and will check for each of them 
    if it is possible to get to an end(reach the final state).
    





