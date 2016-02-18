Supported:

* Integer numbers ('123', '44')
* 4 operators '+', '-', '*', '/'

Parenthesis are NOT supported.

Algorithm
---------

## Stage 1

Parse expression to stack of operands s1 and stack of operators s2.

Iterate over string:
1. If it's digit, get until digits ends, parse as int and push into operand stack
2. If it's operator, parse it as enum and save push into operator stack
3. Otherwise, return error

## Stage 2

Evaluate expression

Create 2 additional temporary stacks for operands t1 and operators t2

Until s2 and t2 are not empty:
    Take operator from s2 and push it into t2
    Take operand from s1 and push it into t1
    If next operator from s2 has higher precedence, continue
    Pop operands from s1 and t1 and apply operator from t2. Push result to s1.

Pop final result from s1.

Ideas
-----

Parse expression to tree (AST). With this 4 stacks will be unnecessary and we'll be able to parse parenthesis.
