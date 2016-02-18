package com.dzyoba.expr;

/**
 * Operator token
 */
public class Operator implements Token
{
    private char operator;

    public Operator(String s)
    {
        switch (s)
        {
            case "+":
            case "-":
            case "*":
            case "/":
                operator = s.charAt(0);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operator " + s);
        }
    }

    @Override
    public String toString() { return String.valueOf(operator); }

    @Override
    public boolean equals(Object o)
    {
        Operator op = (Operator) o;
        return this.operator == op.operator;
    }
}
