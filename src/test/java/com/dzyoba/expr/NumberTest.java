package com.dzyoba.expr;

import junit.framework.TestCase;

public class NumberTest extends TestCase {
    public void testSimple() {
        Number o = new Number("123");
        // Number internal representation is double
        assertEquals("123.0", o.toString());
    }

    public void testDouble() {
        Number o = new Number("123.45");
        assertEquals("123.45", o.toString());
    }

    public void testFormatException() {
        boolean thrown = false;
        try {
            new Number("123a");
        } catch (NumberFormatException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}