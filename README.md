# Project #3: Experiments with Hashing

* Author: Ellis Rodriguez
* Class: CS321 Section 002
* Semester: Spring 2025

**Note: Download this file and add to your repo as README.md. Don't cut and paste it as you won't get the formatting Markdown tags!**

## Overview
The program experiments with Hashing. It gives the user an idea of the  
relationship between the number of probes per element in the Hashtable and
the load factor. The program show the user the performance statistics with hashing
by showing how many elements were inserted into the hashtable, how many probes on average
it took to insert an object into the Hashtable, the size of the hashtable, and how many
of the elements were duplicates. 


Concisely explain what the program does. If this exceeds a couple of
sentences, you're going too far. Generally you should be pulling this
right from the project specification. I don't want you to just cut and
paste, but paraphrase what is stated in the project specification.

## Reflection
Most of the project went well for me but it did take a couple of iterations
to finally get everything to work well. The HashtableExperiment.java file 
went well for the most part since I just to had to design how the simulation 
would go. The inheritance also went well for me since I was able to review the 
examples that were provided for me so it was not too difficult to extend the 
LinearProbing and DoubleHashing classes. I enjoyed implementing the Hashtable,
LinearProbing, and DoubleHashing classes since that was where the core functionality 
for the program was. Working on those classes also helped me understand at a deeper level
of how Hashing works. 

The part that was less desirable for me was implementing the HashtableExperiment class since
it was just simulating the experiment. There was also a lot that I had to decompose so that it 
would be easy to follow the different parts of the simulation. There was nothing different with doing
this portion compared to other projects. I ran into an issue with the insert() method in the HashtableExperiment 
class. I would hash twice for the intial probe before incrementing the index which would mess with the average 
number of probes.  

## Compiling and Using
To compile the code, the user must input "javac HashtableExperiment.java". The program does require user input. The 
quickest way for the user to begin using the program is by inputting "java HashtableExperiment". After inputting this, 
the user will be given a usage message of what the user must input along with accepted values for each parameter. 

## Results 

For Random Integers: 

Hashing Technique | Load Factor | Avg. Num. of Probes
--- | --- | ---
Linear Probing | 0.50 | 1.51
Double Hashing | 0.50 | 1.39 
Linear Probing | 0.60 | 1.76 
Double Hashing | 0.60 | 1.53
Linear Probing | 0.70 | 2.17
Double Hashing | 0.70 | 1.71
Linear Probing | 0.80 | 3.03
Double Hashing | 0.80 | 2.01
Linear Probing | 0.90 | 5.33
Double Hashing | 0.90 | 2.57
Linear Probing | 0.95 | 10.73
Double Hashing | 0.95 | 3.14
Linear Probing | 0.99 | 34.16
Double Hashing | 0.99 | 4.69

For Date Objects: 

Hashing Technique | Load Factor | Avg. Num. of Probes
--- | --- | ---
Linear Probing | 0.50 | 1.28
Double Hashing | 0.50 | 1.38 
Linear Probing | 0.60 | 1.44 
Double Hashing | 0.60 | 1.66
Linear Probing | 0.70 | 1.60
Double Hashing | 0.70 | 1.98
Linear Probing | 0.80 | 1.82
Double Hashing | 0.80 | 2.45
Linear Probing | 0.90 | 2.18
Double Hashing | 0.90 | 3.11
Linear Probing | 0.95 | 2.70
Double Hashing | 0.95 | 3.77
Linear Probing | 0.99 | 5.41
Double Hashing | 0.99 | 5.37

For String Words: 

Hashing Technique | Load Factor | Avg. Num. of Probes
--- | --- | ---
Linear Probing | 0.50 | 1.60
Double Hashing | 0.50 | 1.39
Linear Probing | 0.60 | 2.15
Double Hashing | 0.60 | 1.53
Linear Probing | 0.70 | 3.60
Double Hashing | 0.70 | 1.72
Linear Probing | 0.80 | 6.71
Double Hashing | 0.80 | 2.02
Linear Probing | 0.90 | 19.81
Double Hashing | 0.90 | 2.57
Linear Probing | 0.95 | 110.59
Double Hashing | 0.95 | 3.19
Linear Probing | 0.99 | 471.67
Double Hashing | 0.99 | 4.70
## Sources used

https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html - Used this source to figure out how to override the toString() and equals() methods. 

https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html - Used this source to figure out how to read from a file. 

https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html - Used this source to figure out how to print into a file with PrintWriter. 

https://www.geeksforgeeks.org/sieve-of-eratosthenes/ - Used this source to figure out how to generate prime numbers for the TwinPrimeGenerator class. 

----------

## Notes

* This README.md template is using Markdown. Here is some help on using Markdown: 
[markdown cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)


* Markdown can be edited and viewed natively in most IDEs such as Eclipse and VS Code. Just toggle
between the Markdown source and preview tabs.

* To preview your README.md output online, you can copy your file contents to a Markdown editor/previewer
such as [https://stackedit.io/editor](https://stackedit.io/editor).
