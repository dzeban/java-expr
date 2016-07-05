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
        Stack<Operator> stack = new Stack<>();

        for (Token t : tokens) {
            switch (t.tokenType) {
                case NUMBER:
                    output.add(t);
                    break;
                case OPERATOR:
                    Operator opCur = (Operator) t;

                    // For ')' we pop everything until '('
                    // and continue to the next token
                    if (opCur.type == OperatorType.RBRACE) {
                        try {
                            Operator op = stack.pop();
                            while (op.type != OperatorType.LBRACE) {
                                output.add(op);
                                op = stack.pop();
                            }
                        } catch (EmptyStackException e) {
                            throw new IllegalArgumentException("Unbalanced parenthesis");
                        }
                        continue; // Avoid pushing RBRACE on the stack
                    }

                    // Pop operators with higher precedence
                    while (!stack.empty()) {
                        Operator opOnStack = stack.peek();

                        boolean precedenceLeftCond =
                                opCur.getAssociativity() == Associativity.LEFT
                                && opCur.compareTo(opOnStack) <= 0
                                && opOnStack.type != OperatorType.LBRACE;

                        boolean precedenceRightCond =
                                opCur.getAssociativity() == Associativity.RIGHT
                                && opCur.compareTo(opOnStack) < 0
                                && opOnStack.type != OperatorType.LBRACE;

                        if (precedenceLeftCond || precedenceRightCond)
                            output.add(stack.pop());
                        else
                            break;
                    }

                    // Operators are pushed to the stack
                    stack.push(opCur);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown token " + t);
            }
        }

        // Pop remained operators
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
