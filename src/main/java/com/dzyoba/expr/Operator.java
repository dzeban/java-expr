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
 * Operator token
 */
class Operator extends Token implements Comparable<Operator> {
    private OperatorType operatorType;

    private char symbol;
    private int precedence;
    private Associativity associativity;

    public Operator(String s) {
        tokenType = TokenType.OPERATOR;

        switch (s) {
            case "+":
                operatorType = OperatorType.ADD;
                precedence = 0;
                associativity = Associativity.LEFT;
                break;
            case "-":
                operatorType = OperatorType.SUB;
                precedence = 0;
                associativity = Associativity.LEFT;
                break;
            case "*":
                operatorType = OperatorType.MUL;
                precedence = 1;
                associativity = Associativity.LEFT;
                break;
            case "/":
                operatorType = OperatorType.DIV;
                precedence = 1;
                associativity = Associativity.LEFT;
                break;
            case "^":
                operatorType = OperatorType.POW;
                precedence = 2;
                associativity = Associativity.RIGHT;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operatorType " + s);
        }

        symbol = s.charAt(0);
    }

    public Number apply(Number number1, Number number2) {
        double result;
        double n1 = number1.getValue();
        double n2 = number2.getValue();

        switch (operatorType) {
            case ADD:
                result = n1 + n2;
                break;
            case SUB:
                result = n1 - n2;
                break;
            case MUL:
                result = n1 * n2;
                break;
            case DIV:
                result = n1 / n2;
                break;
            case POW:
                result = Math.pow(n1, n2);
                break;
            default:
                throw new IllegalArgumentException("Invalid operatorType " + operatorType);
        }

        return new Number(result);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Operator)) {
            return false;
        } else {
            Operator op = (Operator) o;
            return this.operatorType == op.operatorType;
        }
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    @Override
    public int compareTo(Operator other) {
        return precedence - other.precedence;
    }
}
