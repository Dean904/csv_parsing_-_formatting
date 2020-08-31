# Simple Paranthesis Validation

6 unique LISP code examples (2 valid, 2 with an extra opening paranthesis, and 2 with an extra closing) feed unit tests that operate on the static ParanthesisValidator function .validate(File file). Implementing using a queue to keep track of opened and closed paranthesis; opening pushes an item to the stack, closing pops. If an EmptyStackException is thrown, validation fails. And if the stack is not empty at the end of the file, validation fails. 

If you want to see some real validation, take a look at my [Recursive-Descent-Parser](https://github.com/Dean904/Recursive-descent-parser) completed in C++ as part of a Programming Language Paradigms course. Its a lexial analyzer (tokenizer) and syntax validator for a subset of the C language. Its basically the first step of an actual compiler!

## Installation

Clone the repository and import 'an existing maven project' into your favorite IDE.
```
git clone https://github.com/Dean904/paranthesis_validation.git
```
[MIT](https://choosealicense.com/licenses/mit/)