package com.dzyoba.expr;

import junit.framework.TestCase;

public class OperatorTest extends TestCase
{
    public void testPlus()
    {
        Operator o = new Operator("+");
        assertEquals("+", o.toString());
    }

    public void testMinus()
    {
        Operator o = new Operator("-");
        assertEquals("-", o.toString());
    }

    public void testMul()
    {
        Operator o = new Operator("*");
        assertEquals("*", o.toString());
    }

    public void testDiv()
    {
        Operator o = new Operator("/");
        assertEquals("/", o.toString());
    }

    public void testUnsupported()
    {
        boolean thrown = false;
        try {
            Operator o = new Operator("#");
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}