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
package org.apache.commons.crypto.cipher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import org.apache.commons.crypto.utils.AES;
import org.junit.jupiter.api.Test;

class CryptoCipherFactoryTest {

    @Test
    void testDefaultCipher() throws GeneralSecurityException, IOException {
        try (CryptoCipher defaultCipher = CryptoCipherFactory.getCryptoCipher(AES.CTR_NO_PADDING)) {
            final String name = defaultCipher.getClass().getName();
            if (OpenSsl.getLoadingFailureReason() == null) {
                assertEquals(OpenSslCipher.class.getName(), name);
            } else {
                assertEquals(JceCipher.class.getName(), name);
            }
        }
    }

    @Test
    void testEmptyCipher() throws GeneralSecurityException, IOException {
        final Properties properties = new Properties();
        properties.setProperty(CryptoCipherFactory.CLASSES_KEY, ""); // TODO should this really mean use the default?
        try (CryptoCipher defaultCipher = CryptoCipherFactory.getCryptoCipher(AES.CBC_NO_PADDING, properties)) {
            final String name = defaultCipher.getClass().getName();
            if (OpenSsl.getLoadingFailureReason() == null) {
                assertEquals(OpenSslCipher.class.getName(), name);
            } else {
                assertEquals(JceCipher.class.getName(), name);
            }
        }
    }

    @Test
    void testInvalidCipher() {
        final Properties properties = new Properties();
        properties.setProperty(CryptoCipherFactory.CLASSES_KEY, "InvalidCipherName");
        assertThrows(GeneralSecurityException.class, () -> CryptoCipherFactory.getCryptoCipher(AES.CBC_NO_PADDING, properties));

    }

    @Test
    void testInvalidTransformation() {
        final Properties properties = new Properties();
        assertThrows(GeneralSecurityException.class, () -> CryptoCipherFactory.getCryptoCipher("AES/Invalid/NoPadding", properties));

    }

    @Test
    void testNoCipher() {
        final Properties properties = new Properties();
        // An empty string currently means use the default
        // However the splitter drops empty fields
        properties.setProperty(CryptoCipherFactory.CLASSES_KEY, ",");
        assertThrows(IllegalArgumentException.class, () -> CryptoCipherFactory.getCryptoCipher(AES.CBC_NO_PADDING, properties));

    }

}
