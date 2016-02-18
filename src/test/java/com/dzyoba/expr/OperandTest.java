package com.dzyoba.expr;

import junit.framework.TestCase;

public class OperandTest extends TestCase
{
    public void testSimple()
    {
        Operand o = new Operand("123");
        // Operand internal representation is double
        assertEquals("123.0", o.toString());
    }

    public void testDouble()
    {
        Operand o = new Operand("123.45");
        assertEquals("123.45", o.toString());
    }

    public void testFormatException()
    {
        boolean thrown = false;
        try {
            Operand o = new Operand("123a");
        } catch (NumberFormatException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}