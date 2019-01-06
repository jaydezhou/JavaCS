Algorithms(4th)



[TOC]



## 1.)Fundamentals

### 1.1)Programming Model

This section under major construction.

Our study of algorithms is based upon implementing them as programs written in the Java programming language. We do so for several reasons:
- Our programs are concise, elegant, and complete descriptions of algorithms.
- You can run the programs to study properties of the algorithms.
- You can put the algorithms immediately to good use in applications.

**Primitive data types and expressions.**A data type is a set of values and a set of operations on those values. The following four primitive data types are the basis of the Java language: 

- *Integers*, with arithmetic operations (`int`)
- *Real numbers*, again with arithmetic operations (`double`)
- *Booleans*, the set of values { *true*, *false* } with logical operations (`boolean`)
- *Characters*, the alphanumeric characters and symbols that you type (`char`)

A Java program manipulates *variables* that are named with *identifiers*. Each variable is associated with a data type and stores one of the permissible data-type values. We use *expressions* to apply the operations associated with each type.

![basic building blocks for Java programs](https://algs4.cs.princeton.edu/11model/images/building-blocks.png)

The following table summarizes the set of values and most common operations on those values for Java's `int`, `double`, `boolean`, and `char` data types.

![primitive data types in Java](https://algs4.cs.princeton.edu/11model/images/primitive.png)

- *Expressions.* Typical expressions are *infix*. When an expression contains more than one operator, the *precedence order* specifies the order in which they are applied: The operators `*` and `/` (and `%`) have higher precedence than (are applied before) the `+` and `-` operators; among logical operators, `!` is the highest precedence, followed by `&&` and and then `||`. Generally, operators of the same precedence are *left associative*(applied left to right). You can use parentheses to override these rules.

- *Type conversion.* Numbers are automatically promoted to a more inclusive type if no information is lost.For example, in the expression `1 + 2.5`, the `1` is promoted to the `double` value `1.0` and the expression evaluates to the `double` value 3.5. A *cast* is a directive to convert a value of one type into a value of another type. For example `(int) 3.7` is `3`. Casting a `double` to an `int` truncates toward zero.

- *Comparisons.* The following *mixed-type* operators compare two values of the same type and produce a `boolean` value:
  - *equal* (`==`)

  - *not equal* (`!=`)

  - *less than* (`<`)

  - *less than or equal* (`<=`)

  - *greater than* (`>`)

  - *greater than or equal* (`>=`)

- *Other primitive types.* Java's `int` has a 32-bit representation; Java's `double` type has a 64-bit representation. Java has five additional primitive data types:
  - 64-bit integers, with arithmetic operations (`long`)

  - 16-bit integers, with arithmetic operations (`short`)

  - 16-bit characters, with arithmetic operations (`char`)

  - 8-bit integers, with arithmetic operations (`byte`)

  - 32-bit single-precision real numbers, with arithmetic operations (`float`)

**Statements.** A Java program is composed of statements, which define the computation by creating and manipulating variables, assigning data-type values to them, and controlling the flow of execution of such operations.

  - *Declarations* create variables of a specified type and name them with identifiers. Java is a *strongly typed* language because the Java compiler checks for consistency. The *scope* of a variable is the part of the program where it is defined.

  - *Assignments* associate a data-type value (defined by an expression) with a variable.

  - *Initializing declarations* combine a declaration with an assignment to initialize a variable at the same time it is declared.

  - Implicit assignments.The following shortcuts are available when our purpose is to modify a variable's value relative to the current value:

    - Increment/decrement operators: the code `i++` is shorthand for `i = i + 1`. The code `++i` is the same except that the expression value is taken *after* the increment/decrement, not before.

    - Other compound operators: the code `i /= 2` is shorthand for `i = i/2`.

  - *Conditionals* provide for a simple change in the flow of execution—execute the statements in one of two blocks, depending on a specified condition. 

  - Loops provide for a more profound change in the flow of execution—execute the statements in a block as long as a given condition is true. We refer to the statements in the block in a loop as the body of the loop.

  - Break and continue. Java supports two additional statements for use within while loops:
    - The `break` statement, which immediately exits the loop
    - The `continue` statement, which immediately begins the next iteration of the loop

  - *For notation.* Many loops follow this scheme: initialize an index variable to some   - value and then use a `while` loop to test a loop continuation condition involving the index variable, where the last statement in the `while` loop increments the index variable. You can express such loops compactly with Java's `for` notation.

  - *Single-statement blocks.* If a block of statements in a conditional or a loop has only a single statement, the curly braces may be omitted.

The following table illustrates different kinds of Java statements.

![Java statements](https://algs4.cs.princeton.edu/11model/images/statements.png)

**Arrays.**An *array* stores a sequence of values that are all of the same type. If we have `N` values, we can use the notation `a[i]` to refer to the `i`th value for any value of `i` from `0` to `N-1`.
- *Creating and initializing an array.* Making an array in a Java program involves three distinct steps:
  - Create the array.
  - Initialize the array values.
  - Declare the array name and type.
- *Default array initialization.* For economy in code, we often take advantage of Java's default array initialization convention and combine all three steps into a single statement. The default initial value is zero for numeric types and `false` for type `boolean`.
- *Initializing declaration.* We can specify the initialization values at compile time, by listing literal values between curly braces, separated by commas.


![declaring, creating, and initializing an array](https://algs4.cs.princeton.edu/11model/images/array.png)

- *Using an array.* Once we create an array, its size is fixed. A program can refer to the length of an array `a[]` with the code `a.length`. Java does *automatic bounds checking*—if you access an array with an illegal index your program will terminate with an[ArrayIndexOutOfBoundsException](http://download.oracle.com/javase/6/docs/api/java/lang/ArrayIndexOutOfBoundsException.html).

- *Aliasing.* An array name refers to the whole array—if we assign one array name to another, then both refer to the same array, as illustrated in the following code fragment.


```
int[] a = new int[N];
...
a[i] = 1234;
...
int[] b = a;
...
b[i] = 5678;   // a[i] is now 5678.
```

This situation is known as *aliasing* and can lead to subtle bugs.

- *Two-dimensional arrays.* A two-dimensional array in Java is an array of one-dimensional arrays. A two-dimensional array may be *ragged* (its arrays may all be of differing lengths), but we most often work with (for appropriate parameters M and N) M-by-N two-dimensional arrays. To refer to the entry in row `i` and column `j` of a two-dimensional array `a[][]`, we use the notation `a[i][j]`.

![anatomy of a static method](https://algs4.cs.princeton.edu/11model/images/static-method.png)

- *Invoking a static method.* A call on a static method is its name followed by expressions that specify argument values in parentheses, separated by commas. When a method is called, its argument variables are initialized with the values of the corresponding expressions in the call. A `return` statement terminates a static method, returning control to the caller. If the static method is to compute a value, that value must be specified in a `return` statement.
- *Properties of methods.* Java methods have the following features:
  - *Arguments are passed by value.* When calling a function, the argument value is fully evaluated and the resulting value is *copied* into argument variable. This is known as *pass by value*. Array (and other object) references are also passed by value: the method cannot change the reference, but it can change the entries in the array (or value of the object).
  - *Method names can be overloaded.* Methods within a class can have the same name, provided they have different signatures. This features is known as *overloading*.
  - *A method has a single return value but may have multiple return statements.* A Java method can provide only one return value. Control goes back to the calling program as soon as the first `return` statement is reached.
  - *A method can have side effects.* A method may use the keyword `void` as its return type, to indicate that it has no return value and produces side effects (consume input, produce output, change entries in an array, or otherwise change the state of the system).
- *Recursion.* A *recursive* method is a method that calls itself either directly or indirectly. There are three important rules of thumb in developing recursive programs:

  - The recursion has a *base case*.
  - Recursive calls must address subproblems that are *smaller* in some sense, so that recursive calls converge to the base case.
  - Recursive calls should not address subproblems that *overlap*.
- *Basic programming model.* A *library of static methods* is a set of static methods that are defined in a Java class. A basic model for Java programming is to develop a program that addresses a specific computational task by creating a library of static methods, one of which is named `main()`.
- *Modular programming.* Libraries of static methods enable *modular programming*, where static methods in one library can call static methods defined in other libraries. This approach has many important advantages.
  - Work with modules of reasonable size
  - Share and reuse code without having to reimplement it
  - Substitute improved implementations
  - Develop appropriate abstract models for addressing programming problems
  - Localize debugging
- *Unit testing.* A best practice in Java programming is to include a `main()` in every library of static methods that tests the methods in the library.
- *External libraries.* We use static methods from four different kinds of libraries, each requiring (slightly) differing procedures for code reuse.
  - Standard system libraries in `java.lang`, including `java.lang.Math`, `java.lang.Integer`, and `java.lang.Double`.
  - Imported system libraries such as `java.util.Arrays`. An `import` statement at the beginning of the program is needed to use such libraries.
  - Other libraries in this book. To use such a program, download the source from the booksite into your working directory or follow [these instructions](https://algs4.cs.princeton.edu/code) for adding [algs4.jar](https://algs4.cs.princeton.edu/code/algs4.jar) to your classpath.
  - The standard libraries that we have developed for use in this book. To use such a program, download the source from the booksite into your working directory or follow [these instructions](https://algs4.cs.princeton.edu/code) for adding [stdlib.jar](https://algs4.cs.princeton.edu/code/stdlib.jar) to your classpath.

To invoke a method from another library, we prepend the library name to the method name for each call: `Math.sqrt()`, `Arrays.sort()`, `BinarySearch.rank()`, and `StdIn.readInt()`.



**APIs.**

- Java libraries.
- Our standard libraries.
- *Your own libraries.*

**Strings.**

![Java's string data type](https://algs4.cs.princeton.edu/11model/images/string.png)

- Concatenation.
- Conversion.
- Automatic conversion.
- *Command-line arguments.*

**Input and output.**

- *Commands and arguments.*

![anatomy of a command](https://algs4.cs.princeton.edu/11model/images/command.png)

- Standard output.
- *Formatted output.*

![printf formatting conventions](https://algs4.cs.princeton.edu/11model/images/printf.png)

- Standard input.
- *Redirection and piping.*

![redirection and piping from the command line](https://algs4.cs.princeton.edu/11model/images/redirect.png)

- Input and output from a file.
- *Standard drawing.*

**Binary search.**Below is a complete Java program [BinarySearch.java](https://algs4.cs.princeton.edu/11model/BinarySearch.java.html) that illustrates many of the basic features of our programming model. It implement a classic algorithm known as *binary search* and tests it for an application known as *whitelist filtering*.

![anatomy of binary search](https://algs4.cs.princeton.edu/11model/images/binary-search-anatomy.png)

The static method `rank()` takes an integer key and a *sorted* array of `int` values as arguments and returns the index of the key if it is present in the array, -1 otherwise. It accomplishes this task by maintaining variables `lo` and `hi` such that the key is in `a[lo..hi]` if it is in the array, then entering into a loop that tests the middle entry in the interval (at index `mid`). If the key is equal to `a[mid]`, the return value is `mid`; otherwise the method cuts the interval size about in half, looking at the left half if the key is less than `a[mid]` and at the right half if the key is greater than `a[mid]`. The process terminates when the key is found or the interval is empty.

![binary search in an ordered array](https://algs4.cs.princeton.edu/11model/images/binary-search.png)

- Development client.
- Whitelisting.
- *Performance.*

**Input and output libraries.**Here is a list of the input and output libraries that we use throughout the textbook and beyond.

| **§**                                    | **PROGRAM**                              | **DESCRIPTION / JAVADOC**                |
| ---------------------------------------- | ---------------------------------------- | ---------------------------------------- |
| [1.5](http://introcs.cs.princeton.edu/15inout/index.php#1.5) | [StdIn.java](https://algs4.cs.princeton.edu/stdlib/StdIn.java.html) | [read numbers and text from standard input](https://algs4.cs.princeton.edu/stdlib/javadoc/StdIn.html) |
| [1.5](http://introcs.cs.princeton.edu/15inout/index.php#1.5) | [StdOut.java](https://algs4.cs.princeton.edu/stdlib/StdOut.java.html) | [write numbers and text to standard output](https://algs4.cs.princeton.edu/stdlib/javadoc/StdOut.html) |
| [1.5](http://introcs.cs.princeton.edu/15inout/index.php#1.5) | [StdDraw.java](https://algs4.cs.princeton.edu/stdlib/StdDraw.java.html) | [draw geometric shapes in a window](https://algs4.cs.princeton.edu/stdlib/javadoc/StdDraw.html) |
| [1.5](http://introcs.cs.princeton.edu/15inout/index.php#1.5) | [StdAudio.java](https://algs4.cs.princeton.edu/stdlib/StdAudio.java.html) | [create, play, and manipulate sound](https://algs4.cs.princeton.edu/stdlib/javadoc/StdAudio.html) |
| [2.2](http://introcs.cs.princeton.edu/22library/index.php#2.2) | [StdRandom.java](https://algs4.cs.princeton.edu/stdlib/StdRandom.java.html) | [generate random numbers](https://algs4.cs.princeton.edu/stdlib/javadoc/StdRandom.html) |
| [2.2](http://introcs.cs.princeton.edu/22library/index.php#2.2) | [StdStats.java](https://algs4.cs.princeton.edu/stdlib/StdStats.java.html) | [compute statistics](https://algs4.cs.princeton.edu/stdlib/javadoc/StdStats.html) |
| [2.2](http://introcs.cs.princeton.edu/22library/index.php#2.2) | [StdArrayIO.java](https://algs4.cs.princeton.edu/stdlib/StdArrayIO.java.html) | [read and write 1D and 2D arrays](https://algs4.cs.princeton.edu/stdlib/javadoc/StdArrayIO.html) |
| [3.1](http://introcs.cs.princeton.edu/31datatype/index.php#3.1) | [In.java](https://algs4.cs.princeton.edu/stdlib/In.java.html) | [read numbers and text from files and URLs](https://algs4.cs.princeton.edu/stdlib/javadoc/In.html) |
| [3.1](http://introcs.cs.princeton.edu/31datatype/index.php#3.1) | [Out.java](https://algs4.cs.princeton.edu/stdlib/Out.java.html) | [write numbers and text to files](https://algs4.cs.princeton.edu/stdlib/javadoc/Out.html) |
| [3.1](http://introcs.cs.princeton.edu/31datatype/index.php#3.1) | [Draw.java](https://algs4.cs.princeton.edu/stdlib/Draw.java.html) | [draw geometric shapes](https://algs4.cs.princeton.edu/stdlib/javadoc/Draw.html) |
| [3.1](http://introcs.cs.princeton.edu/31datatype/index.php#3.1) | [Picture.java](https://algs4.cs.princeton.edu/stdlib/Picture.java.html) | [process digital images](https://algs4.cs.princeton.edu/stdlib/javadoc/Picture.html) |
| [3.2](http://introcs.cs.princeton.edu/32class/index.php#3.2) | [Stopwatch.java](https://algs4.cs.princeton.edu/stdlib/Stopwatch.java.html) | [measure running time](https://algs4.cs.princeton.edu/stdlib/javadoc/Stopwatch.html) |
| [-](http://introcs.cs.princeton.edu/15inout/index.php#-) | [BinaryStdIn.java](https://algs4.cs.princeton.edu/stdlib/BinaryStdIn.java.html) | [read bits from standard input](https://algs4.cs.princeton.edu/stdlib/javadoc/BinaryStdIn.html) |
| [-](http://introcs.cs.princeton.edu/15inout/index.php#-) | [BinaryStdOut.java](https://algs4.cs.princeton.edu/stdlib/BinaryStdOut.java.html) | [write bits to standard output](https://algs4.cs.princeton.edu/stdlib/javadoc/BinaryStdOut.html) |
| [-](http://introcs.cs.princeton.edu/15inout/index.php#-) | [BinaryIn.java](https://algs4.cs.princeton.edu/stdlib/BinaryIn.java.html) | [read bits from files and URLs](https://algs4.cs.princeton.edu/stdlib/javadoc/BinaryIn.html) |
| [-](http://introcs.cs.princeton.edu/15inout/index.php#-) | [BinaryOut.java](https://algs4.cs.princeton.edu/stdlib/BinaryOut.java.html) | [write bits to files](https://algs4.cs.princeton.edu/stdlib/javadoc/BinaryOut.html) |

We briefly describe the input and output libraries and include a sample client.

**Standard input and standard output.** [StdIn.java](https://algs4.cs.princeton.edu/11model/StdIn.java.html) and [StdOut.java](https://algs4.cs.princeton.edu/11model/StdOut.java.html) are libraries for reading in numbers and text from standard input and printing out numbers and text to standard output. Our versions have a simpler interface than the corresponding Java ones (and provide a few technical improvements). [RandomSeq.java](https://algs4.cs.princeton.edu/11model/RandomSeq.java.html) generates random numbers in a given range. [Average.java](https://algs4.cs.princeton.edu/11model/Average.java.html) reads in a sequence of real numbers from standard input and prints their average on standard output.

```
% java Average
10.0 5.0 6.0 3.0 7.0 32.0
3.14 6.67 17.71
<Ctrl-d>
Average is 10.05777777777778
```

[In.java](https://algs4.cs.princeton.edu/11model/In.java.html) and [Out.java](https://algs4.cs.princeton.edu/11model/Out.java.html) are object-oriented versions that support multiple input and output streams, including reading from a file or URL and writing to a file.

**Standard drawing.** [StdDraw.java](https://algs4.cs.princeton.edu/11model/StdDraw.java.html) is an easy-to-use library for drawing geometric shapes, such as points, lines, and circles. [RightTriangle.java](https://algs4.cs.princeton.edu/11model/RightTriangle.java.html)draws a right triangle and a circumscribing circle.

[Draw.java](https://algs4.cs.princeton.edu/11model/Draw.java.html) is an object-oriented versions that support drawing in multiple windows.

**Standard audio.** [StdAudio.java](https://algs4.cs.princeton.edu/11model/StdAudio.java.html) is an easy-to-use library for synthesizing sound. [Tone.java](https://algs4.cs.princeton.edu/11model/Tone.java.html) reads in a frequency and duration from the command line, and it sonifies a sine wave of the given frequency for the given duration.

```
% java Tone 440.0 3.0
```

**Image processing.** [Picture.java](https://algs4.cs.princeton.edu/11model/Picture.java.html) is an easy-to-use library for image processing. [Scale.java](https://algs4.cs.princeton.edu/11model/Scale.java.html) takes the name of a picture file and two integers (width w and height h) as command-line arguments and scales the image to w-by-h.

**% java Scale mandrill.jpg 298 298**

![298-by-298](https://algs4.cs.princeton.edu/11model/mandrill.jpg)

**% java Scale mandrill.jpg 200 200**

![200-by-400](https://algs4.cs.princeton.edu/11model/mandrill200x200.jpg)

**% java Scale mandrill.jpg 200 400**

![200-by-400](https://algs4.cs.princeton.edu/11model/mandrill200x400.jpg)



#### Q + A

**Q.** How important is it to use a good shuffling algorithm?

**A.** Here's an [amusing anecdote](http://www.datamation.com/entdev/article.php/616221/How-We-Learned-to-Cheat-at-Online-Poker-A-Study-in-Software-Security.htm) of what happens when you don't do it correctly (and you're business is online poker!). If you're running an online casino, here's the recommended approach for shuffling a deck of cards: (i) get a cryptographically secure pseudo-random number generator, (ii) assign a random 64-bit number to each card, (iii) sort the cards according to their number.



#### Creative Problems

27. **Binomial distribution.** Estimate the number of recursive calls that would be used by the method call `binomial1(100, 50, .25)` in [Binomial.java](https://algs4.cs.princeton.edu/11model/Binomial.java.html). Develop a better implementation that is based on saving computed values in an array.



#### Web Exercises

1. **Sattolo's algorithm.** Write a program [Sattolo.java](https://algs4.cs.princeton.edu/11model/Sattolo.java.html) that generates a unifomly distributed cycle of length N using [Sattolo's algorithm](http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#Sattolo.27s_algorithm).

2. **Wget.** Write a program [Wget.java](https://algs4.cs.princeton.edu/11model/Wget.java.html) that reads in data from the URL specified on the command line and saves it in a file with the same name.

 ```
   % java Wget http://introcs.cs.princeton.edu/data/codes.csv
   % more codes.csv
   United States,USA,00
   Alabama,AL,01
   Alaska,AK,02
   ...
 ```
3. **Right triangle.** Write a client [RightTriangle.java](https://algs4.cs.princeton.edu/11model/RightTriangle.java.html) that draws a right triangle and a circumscribing circle.


% java RightTriangle

![right triangle and circumscribing circle](https://algs4.cs.princeton.edu/11model/right-triangle.png)

4. **Bouncing ball.** Write a program [BouncingBall.java](https://algs4.cs.princeton.edu/11model/BouncingBall.java.html) that illustrates the motion of a bouncing ball.




## 2)Sorting


### 2.1)Elementary Sorts

