package com.dzyoba.expr;


/**
 * Number token
 */
public class Number extends Token
{
    private double value;

    public Number(String s)
    {
        type = TokenType.OPERAND;
        value = Double.parseDouble(s);
    }

    public Number(double d)
    {
        type = TokenType.OPERAND;
        value = d;
    }

    @Override
    public String toString() { return String.valueOf(value); }

    public double getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object o)
    {
        Number op = (Number)o;
        return this.value == op.value;
    }

    public Number applyOperator(Operator operator, Number other)
    {
        double result;

        switch (operator.operator)
        {
            case ADD:
                result = value + other.getValue();
                break;
            case SUB:
                result = value - other.getValue();
                break;
            default:
                throw new IllegalArgumentException("Invalid operator " + operator.operator);
        }

        return new Number(result);
    }
}
