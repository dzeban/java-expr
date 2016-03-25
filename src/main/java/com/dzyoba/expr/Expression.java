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

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Collection;

/**
 * Expression parser and evaluator
 */
public class Expression
{
    Stack<Number> numbers = new Stack<>();
    Stack<Operator> operators = new Stack<>();

    /**
     * Expression constructor does parsing
     * @param exp String with arithmetic expression
     */
    public Expression(String exp)
    {
        Collection<Token> tokens = TokenBuilder.parse(exp);
        for (Token t : tokens)
        {
            switch (t.type)
            {
                case OPERAND:
                    numbers.push((Number)t);
                    break;
                case OPERATOR:
                    operators.push((Operator)t);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid token " + t);
            }
        }
    }

    public double evaluate()
    {
        while (!operators.empty())
        {
            Operator op;
            Number operand1, operand2, result;

            op = operators.pop();
            try
            {
                operand2 = numbers.pop();
                operand1 = numbers.pop();
            }
            catch (EmptyStackException e)
            {
                throw new IllegalArgumentException("Invalid (unbalanced) expression");
            }

            result = operand1.applyOperator(op, operand2);
            numbers.push(result);
        }

        // If we evaluated all operators, but some operands left,
        // that's an invalid expression
        if (numbers.size() != 1)
        {
            throw new IllegalArgumentException("Invalid (unbalanced) expression");
        }

        return numbers.pop().getValue();
    }
}
