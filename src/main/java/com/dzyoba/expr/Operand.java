package com.dzyoba.expr;


/**
 * Operand token
 */
public class Operand extends Token
{
    public double value;

    public Operand(String s)
    {
        type = TokenType.OPERAND;
        value = Double.parseDouble(s);
    }

    public Operand(double d)
    {
        type = TokenType.OPERAND;
        value = d;
    }

    @Override
    public String toString() { return String.valueOf(value); }

    @Override
    public boolean equals(Object o)
    {
        Operand op = (Operand)o;
        return this.value == op.value;
    }

    public Operand applyOperator(Operator operator, Operand other)
    {
        double result;

        switch (operator.operator)
        {
            case ADD:
                result = value + other.value;
                break;
            case SUB:
                result = value - other.value;
                break;
            case MUL:
                result = value * other.value;
                break;
            case DIV:
                result = value / other.value;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator " + operator.operator);
        }

        return new Operand(result);
    }
}
