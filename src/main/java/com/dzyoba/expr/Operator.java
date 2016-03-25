package com.dzyoba.expr;

/**
 * Operator token
 */
public class Operator extends Token
{
    public char symbol;
    public OPERATOR operator;


    public Operator(String s)
    {
        type = TokenType.OPERATOR;

        switch (s)
        {
            case "+":
                operator = OPERATOR.ADD;
                break;
            case "-":
                operator = OPERATOR.SUB;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operator " + s);
        }

        symbol = s.charAt(0);
    }

    @Override
    public String toString() { return String.valueOf(symbol); }

    @Override
    public boolean equals(Object o)
    {
        Operator op = (Operator) o;
        return this.operator == op.operator;
    }
}

enum OPERATOR
{
    ADD, SUB
}

