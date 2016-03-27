/*
Copyright 2016 Alex Dzyoba <alex@dzyoba.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.dzyoba.expr;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Token builder that parses string into collection of tokens
 */
class TokenBuilder {
    public static Collection<Token> parse(String exp) {
        Collection<Token> tokens = new LinkedList<>();
        StringBuilder tokenBuilder = new StringBuilder();

        for (Character current : exp.toCharArray()) {
            if (Character.isSpaceChar(current))
                continue;

            int length = tokenBuilder.length();
            if (length != 0) {
                char last = tokenBuilder.charAt(length - 1);

                // End of number
                if (Character.isDigit(last) && !Character.isDigit(current)) {
                    // Token is ready
                    tokens.add(new Number(tokenBuilder.toString()));
                    // Reset builder
                    tokenBuilder = new StringBuilder();
                }
                // End of operatorType
                else if (!Character.isDigit(last) && Character.isDigit(current)) {
                    // Token is ready
                    tokens.add(new Operator(tokenBuilder.toString()));
                    // Reset builder
                    tokenBuilder = new StringBuilder();
                }
                // The only valid case left is several consecutive digits that must be accumulated
                // in builder, that's what we do on every iteration (see append below)
                else if (!(Character.isDigit(last) && Character.isDigit(current))) {
                    throw new IllegalArgumentException("Parsing failure: Invalid expression");
                }
            }

            tokenBuilder.append(current);
        }

        // Insert last token
        if (Character.isDigit(tokenBuilder.charAt(0))) {
            tokens.add(new Number(tokenBuilder.toString()));
        } else {
            tokens.add(new Operator(tokenBuilder.toString()));
        }

        return tokens;
    }
}
