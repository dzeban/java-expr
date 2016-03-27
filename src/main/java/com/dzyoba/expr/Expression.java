/*
Copyright 2016 Alex Dzyoba <alex@dzyoba.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.dzyoba.expr;

import java.util.*;

/**
 * Expression parser and evaluator
 */
class Expression {
    /**
     * expression in a reversed polish notation
     */
    private final Collection<Token> expression;

    /**
     * Expression constructor does parsing
     *
     * @param exp String with arithmetic expression
     */
    public Expression(String exp) {
        Collection<Token> tokens = TokenBuilder.parse(exp);
        expression = convertToRPN(tokens);
    }

    private Collection<Token> convertToRPN(Collection<Token> tokens) {
        Collection<Token> output = new LinkedList<>();
        Stack<Token> stack = new Stack<>();

        for (Token t : tokens) {
            switch (t.tokenType) {
                case NUMBER:
                    output.add(t);
                    break;
                case OPERATOR:
                    while (!stack.empty()) {
                        Operator opCur = (Operator) t;
                        Operator opOnStack = (Operator) stack.peek();

                        boolean precedenceLeftCond =
                                opCur.getAssociativity() == Associativity.LEFT
                                && opCur.compareTo(opOnStack) <= 0;

                        boolean precedenceRightCond =
                                opCur.getAssociativity() == Associativity.RIGHT
                                && opCur.compareTo(opOnStack) < 0;

                        if (precedenceLeftCond || precedenceRightCond)
                            output.add(stack.pop());
                        else
                            break;
                    }
                    stack.push(t);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown token " + t);
            }
        }

        while (!stack.empty())
            output.add(stack.pop());

        return output;
    }

    public double evaluate() {
        Stack<Token> stack = new Stack<>();

        for (Token t : expression) {
            switch (t.tokenType) {
                case NUMBER:
                    stack.push(t);
                    break;
                case OPERATOR:
                    Operator op = (Operator) t;
                    Number n1, n2;
                    try {
                        n2 = (Number) stack.pop();
                        n1 = (Number) stack.pop();

                        stack.push(op.apply(n1, n2));
                    } catch (EmptyStackException e) {
                        throw new IllegalArgumentException("Invalid expression");
                    }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return ((Number) stack.pop()).getValue();
    }
}
