package com.dzyoba.expr;

import junit.framework.TestCase;

/**
 * Created by adzyoba on 3/25/16.
 */
public class ExpressionTest extends TestCase {
    public void testSimpleAdd() throws Exception {
        Expression expr = new Expression("1+2+3");
        assertEquals(6.0, expr.evaluate());
    }

    public void testAddSubMix() throws Exception {
        Expression expr = new Expression("1+2-3");
        assertEquals(0.0, expr.evaluate());
    }

    public void testNegativeValue() throws Exception {
        Expression expr = new Expression("4 - 3 - 2 - 1");
        assertEquals(-2.0, expr.evaluate());
    }

    public void testInvalidExpr() throws Exception {
        boolean thrown = false;
        Expression expr = new Expression("1+2+");

        try {
            expr.evaluate();
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    public void testAllOperators() throws Exception {
        Expression expr = new Expression("1 * 2 - 3 / 4 * 5 - 6");
        assertEquals( -7.75, expr.evaluate());
    }
}