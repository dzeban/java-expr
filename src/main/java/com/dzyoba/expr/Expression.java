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

import java.util.Stack;
import java.util.Collection;

/**
 * Expression parser and evaluator
 */
public class Expression
{
    Stack<Token> operands = new Stack<Token>();
    Stack<Token> operators = new Stack<Token>();

    /**
     * Expression constructor does parsing
     * @param exp String with arithmetic expression
     */
    public Expression(String exp)
    {
        Collection<Token> tokens = TokenBuilder.parse(exp);
        for (Token t : tokens)
        {
            System.out.println(t);
        }
    }
}
