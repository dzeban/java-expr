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

import java.util.IllegalFormatException;
import java.util.Stack;
import java.util.Collection;

/**
 * Expression parser and evaluator
 */
public class Expression
{
    Stack<Operand> operands = new Stack<>();
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
                    operands.push((Operand)t);
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
        // Temp stacks that we need
        // because of operator precedence
        Stack<Operand> ns = new Stack<>();
        Stack<Operator> ts = new Stack<>();

        do{
            if (!operands.isEmpty())
                ns.push(operands.pop());

            if (!operators.isEmpty())
                ts.push(operators.pop());

            // If next operator has higher precedence
            if (operators.peek().compareTo(ts.peek()) > 0)
                continue;

            Operand op1, op2, result;
            op1 = operands.pop();
            op2 = ns.pop();

            result = op1.applyOperator(ts.pop(), op2);
            operands.push(result);

        } while(!ns.empty() && !ts.empty());

        return operands.pop().value;
    }

}
