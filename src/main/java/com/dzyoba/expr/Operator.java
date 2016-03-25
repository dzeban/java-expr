package com.dzyoba.expr;

/**
 * Operator token
 */
public class Operator extends Token implements Comparable<Operator>
{
    public char symbol;
    public OPERATOR operator;
    private int precedence;
    private ASSOCIATIVITY associativity;

    public Operator(String s)
    {
        type = TokenType.OPERATOR;

        switch (s)
        {
            case "+":
                operator = OPERATOR.ADD;
                precedence = 0;
                associativity = ASSOCIATIVITY.LEFT;
                break;
            case "-":
                operator = OPERATOR.SUB;
                precedence = 0;
                associativity = ASSOCIATIVITY.LEFT;
                break;
            case "*":
                operator = OPERATOR.MUL;
                precedence = 1;
                associativity = ASSOCIATIVITY.LEFT;
                break;
            case "/":
                operator = OPERATOR.DIV;
                precedence = 1;
                associativity = ASSOCIATIVITY.LEFT;
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

    public ASSOCIATIVITY getAssociativity()
    {
        return associativity;
    }

    public int compareTo(Operator other)
    {
        return precedence - other.precedence;
    }
}

enum OPERATOR
{
    ADD, SUB, MUL, DIV
}

enum ASSOCIATIVITY
{
    LEFT, RIGHT, NONE
}

