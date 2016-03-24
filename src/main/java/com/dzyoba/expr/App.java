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

/**
 * jexpr application launcher
 *
 */
public class App 
{
    private static String concatenateArgs(String[] args)
    {
        String expression = "";
        for(String s : args)
        {
            expression += s;
        }
        return expression;
    }

    public static void main( String[] args )
    {
        String str = concatenateArgs(args);
        System.out.println(str);

        Expression expr = new Expression(str);
        System.out.println(expr.evaluate());
    }
}
