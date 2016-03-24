package com.dzyoba.expr;

/**
 * Operator token
 */
public class Operator extends Token implements Comparable<Operator>
{
    public char operator_char;
    public OP operator;
    public short precedence;


    public Operator(String s)
    {
        type = TokenType.OPERATOR;

        switch (s)
        {
            case "+":
                operator = OP.ADD;
                precedence = 0;
                break;
            case "-":
                operator = OP.SUB;
                precedence = 0;
                break;
            case "*":
                operator = OP.MUL;
                precedence = 1;
                break;
            case "/":
                operator = OP.DIV;
                precedence = 1;
                break;
            default:
                throw new UnsupportedOperationException("Unsupported operator " + s);
        }

        operator_char = s.charAt(0);
    }

    @Override
    public String toString() { return String.valueOf(operator_char); }

    @Override
    public boolean equals(Object o)
    {
        Operator op = (Operator) o;
        return this.operator == op.operator;
    }

    public int compareTo(Operator other)
    {
        return precedence - other.precedence;
    }
}

enum OP
{
    ADD, SUB, MUL, DIV
}

