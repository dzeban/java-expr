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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Token builder that parses string into collection of tokens
 */
class TokenBuilder {
    public static Collection<Token> parse(String exp) {
        Collection<Token> tokens = new ArrayList<>();

        char[] expChars = exp.toCharArray();
        for (int i = 0; i < expChars.length; i++) {
            if (Character.isDigit(expChars[i])) {
                StringBuilder numberBuilder = new StringBuilder();
                do {
                    numberBuilder.append(expChars[i]);
                    ++i;
                    if (i == expChars.length) {
                        break;
                    }
                } while(Character.isDigit(expChars[i]));

                tokens.add(new Number(numberBuilder.toString()));
            }

            if (i == expChars.length) {
                break;
            }

            if (Character.isSpaceChar(expChars[i])) {
                continue;
            }

            tokens.add(new Operator(String.valueOf(expChars[i])));
        }
        return tokens;
    }
}
