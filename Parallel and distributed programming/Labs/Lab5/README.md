DOCUMENTATION
-

The program uses a Polynomial model whichi receives:

- a number(int) : the grade of the polynomial

- a List\<Integer>: the coefficients for that polynomial



We have a class PolynomialOperations which have all 
the operations we need in order to solve the requirement

- sum(Polynomial A, Polynomial B): returns the sum for these 2

- substract(Polynomial A, Polynomial B): returns the difference of these 2

- shitft(Polynomial toBeShifter, Integer by): shifts the polynomial by n positions

- multiplySequencially : multiplys sequencially the two polynomials 

- multiplyParallel : it splits the result into polynomial_result_size / NO_Threads

- KaratsubaSequencially : multiply sequencially the two polynomials using the karatsuba algorithm

- KaratsubaParallel: splits the work in a divide and conquare way


