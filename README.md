Simple Java calculator
======================

`java-expr` reads arithmetic expression from command-line arguments and evaluate
it. Expression evaluation works by converting input to reverse Polish notation
using Shunting-yard algorithm and evaluating it with simple stack-based
algorithm.

Supported operations are `+`, `-`, `*`, `/`. Parenthesis are not supported
(yet). Only integer numbers are supported.

Examples
--------

    $ java com.dzyoba.expr.App 1+2+3
    1+2+3
    6.0

    $ java com.dzyoba.expr.App 1+2-3/4*5
    1+2-3/4*5
    -0.75


