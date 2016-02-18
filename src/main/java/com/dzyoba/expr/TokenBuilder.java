package com.dzyoba.expr;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Token builder that parses string into collection of tokens
 */
public class TokenBuilder
{
    public static Collection<Token> parse(String exp)
    {
        Collection<Token> tokens = new LinkedList<Token>();
        StringBuilder tokenBuilder = new StringBuilder();

        for (Character current : exp.toCharArray())
        {
            if (Character.isSpaceChar(current))
                continue;

            int length = tokenBuilder.length();
            if (length != 0)
            {
                char last = tokenBuilder.charAt(length - 1);

                // End of number
                if (Character.isDigit(last) && !Character.isDigit(current))
                {
                    // Token is ready
                    tokens.add(new Operand(tokenBuilder.toString()));
                    // Reset builder
                    tokenBuilder = new StringBuilder();
                }
                // End of operator
                else if (!Character.isDigit(last) && Character.isDigit(current))
                {
                    // Token is ready
                    tokens.add(new Operator(tokenBuilder.toString()));
                    // Reset builder
                    tokenBuilder = new StringBuilder();
                }
                // The only valid case left is several consecutive digits that must be accumulated in builder,
                // that's what we do on every iteration (see append below)
                else if (!(Character.isDigit(last) && Character.isDigit(current)))
                {
                    throw new IllegalArgumentException("Parsing failure: Invalid expression");
                }
            }

            tokenBuilder.append(current);
        }

        // Insert last token
        if (Character.isDigit(tokenBuilder.charAt(0)))
        {
            tokens.add(new Operand(tokenBuilder.toString()));
        }
        else
        {
            tokens.add(new Operator(tokenBuilder.toString()));
        }

        return tokens;
    }
}
