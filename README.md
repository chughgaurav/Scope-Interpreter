Scope-Interpreter
=================
Scope:
Many programming languages implement scope for variables � and they are
context sensitive. For example a global scope variable will be over-ridden using
local variable definition. You need to implement simple interpreter which tells us
current value of a variable, if its not defined then value should be zero, Input will
start with �[� and will end with matching �]�

Sample Input:
[
a 10
print a
b 20
[
a 10
print a 
print b
b 23
print b
b a
print b
b 23
]
print a
print b
print c
]

Sample output
10
10
20
23
10
10
20
0

Sample Input
[
print a
]
Sample Output
0

Sample Input
[
]
Sample Output


- Developed by Gaurav Chugh (chughgaurav@gmail.com)