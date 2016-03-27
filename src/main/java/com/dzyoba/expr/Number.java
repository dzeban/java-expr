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
 * Number token
 */
public class Number extends Token {
    private final double value;

    public Number(String s) {
        tokenType = TokenType.NUMBER;
        value = Double.parseDouble(s);
    }

    public Number(double d) {
        tokenType = TokenType.NUMBER;
        value = d;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Number)) {
            return false;
        } else {
            Number op = (Number) o;
            return this.value == op.value;
        }
    }
}
