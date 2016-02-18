package com.dzyoba.expr;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.LinkedList;

public class TokenBuilderTest extends TestCase {

    public void testParse() throws Exception
    {
        String expr = "1+2+3";
        Collection<Token> expected = new LinkedList<>();
        expected.add(new Operand("1"));
        expected.add(new Operator("+"));
        expected.add(new Operand("2"));
        expected.add(new Operator("+"));
        expected.add(new Operand("3"));

        Collection<Token> tokens = TokenBuilder.parse(expr);
        assertEquals(expected, tokens);
    }
}