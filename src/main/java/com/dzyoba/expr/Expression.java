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
public class Expression
{
    /** expression in a reversed polish notation */
    private Collection<Token> expression;

    /**
     * Expression constructor does parsing
     * @param exp String with arithmetic expression
     */
    public Expression(String exp)
    {
        Collection<Token> tokens = TokenBuilder.parse(exp);
        expression = convertToRPN(tokens);
    }

    private Collection<Token> convertToRPN(Collection<Token> tokens)
    {
        Collection<Token> output = new LinkedList<>();
        Stack<Token> stack = new Stack<>();

        for (Token t : tokens)
        {
            switch (t.type)
            {
                case NUMBER:
                    output.add(t);
                    break;
                case OPERATOR:
                    while (!stack.empty())
                    {
                        Operator op_cur = (Operator)t;
                        Operator op_on_stack = (Operator)stack.peek();

                        boolean left_assoc_cond = op_cur.getAssociativity() == ASSOCIATIVITY.LEFT &&
                                                  op_cur.compareTo(op_on_stack) <= 0;

                        boolean right_assoc_cond = op_cur.getAssociativity() == ASSOCIATIVITY.RIGHT &&
                                                   op_cur.compareTo(op_on_stack) < 0;

                        if (left_assoc_cond || right_assoc_cond)
                            output.add(stack.pop());
                        else
                            break;
                    }
                    stack.push(t);
                    break;
                default:
                    throw new IllegalArgumentException("Unknwon token " + t);
            }
        }

        while (!stack.empty())
            output.add(stack.pop());

        return output;
    }

    public double evaluate()
    {
        Stack<Token> stack = new Stack<>();

        for (Token t : expression)
        {
            switch (t.type)
            {
                case NUMBER:
                    stack.push(t);
                    break;
                case OPERATOR:
                    Operator op = (Operator)t;
                    Number n1, n2;
                    try
                    {
                        n2 = (Number) stack.pop();
                        n1 = (Number) stack.pop();
                        stack.push(n1.applyOperator(op, n2));
                    }
                    catch (EmptyStackException e)
                    {
                        throw new IllegalArgumentException("Invalid expression");
                    }
            }
        }

        if (stack.size() != 1)
        {
            throw new IllegalArgumentException("Invalid expression");
        }

        return ((Number)stack.pop()).getValue();
    }
}
