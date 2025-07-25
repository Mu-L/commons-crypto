/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.apache.commons.crypto.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.cipher.CryptoCipherFactory;
import org.apache.commons.crypto.cipher.CryptoCipherFactory.CipherProvider;
import org.apache.commons.crypto.random.CryptoRandom;
import org.apache.commons.crypto.random.CryptoRandomFactory;
import org.apache.commons.crypto.random.CryptoRandomFactory.RandomProvider;
import org.junit.jupiter.api.Test;

/**
 * Test the enums used to define the internal implementation classes
 */
class EnumTest {

    private void checkImplClass(final CipherProvider value) {
        final Class<? extends CryptoCipher> implClass = value.getImplClass();
        assertTrue(CryptoCipher.class.isAssignableFrom(implClass), implClass.toString());
        assertEquals(value.getClassName(), implClass.getName());
    }

    private void checkImplClass(final RandomProvider value) {
        final Class<? extends CryptoRandom> implClass = value.getImplClass();
        assertTrue(CryptoRandom.class.isAssignableFrom(implClass), implClass.toString());
        assertEquals(value.getClassName(), implClass.getName());
    }

    @Test
    void testCipher() throws Exception {
        for (final CipherProvider value : CryptoCipherFactory.CipherProvider.values()) {
            ReflectionUtils.getClassByName(value.getClassName());
            checkImplClass(value);
        }
    }

    @Test
    void testRandom() throws Exception {
        for (final RandomProvider value : CryptoRandomFactory.RandomProvider.values()) {
            ReflectionUtils.getClassByName(value.getClassName());
            checkImplClass(value);
        }
    }

    // TODO check if any implementations of CryptoRandom or CryptoCipher are missing from the values

}
