package com.dzyoba.expr;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class TokenBuilderTest extends TestCase {

    public void testParse() throws Exception {
        String expr = "1+2+3";
        Collection<Token> expected = new ArrayList<>();
        expected.add(new Number("1"));
        expected.add(new Operator("+"));
        expected.add(new Number("2"));
        expected.add(new Operator("+"));
        expected.add(new Number("3"));

        Collection<Token> tokens = TokenBuilder.parse(expr);
        assertEquals(expected, tokens);
    }

    public void testMultidigitNumbers() throws Exception {
        String expr = "123 * 456 - 2";
        Collection<Token> expected = new ArrayList<>();
        expected.add(new Number("123"));
        expected.add(new Operator("*"));
        expected.add(new Number("456"));
        expected.add(new Operator("-"));
        expected.add(new Number("2"));

        Collection<Token> tokens = TokenBuilder.parse(expr);
        assertEquals(expected, tokens);
    }
}