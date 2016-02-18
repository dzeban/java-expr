package com.dzyoba.expr;

/**
 * Operand token
 */
public class Operand implements Token
{
    private double value;

    public Operand(String s) { value = Double.parseDouble(s); }

    @Override
    public String toString() { return String.valueOf(value); }

    @Override
    public boolean equals(Object o)
    {
        Operand op = (Operand)o;
        return this.value == op.value;
    }
}
