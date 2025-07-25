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
package org.apache.commons.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OsInfoTest {

    private static final String EXPECTED_PATH_PROPERTY = "OsInfoTest.expectedPath";
    @Test
    void testMain() {
        OsInfo.main(new String[0]);
        OsInfo.main(new String[] { "--os" });
        OsInfo.main(new String[] { "--arch" });

        final String expectedPath = System.getProperty(EXPECTED_PATH_PROPERTY, "");
        if (expectedPath.isEmpty()) {
            System.out.println("Path was not checked");
        } else {
            assertEquals(expectedPath, OsInfo.getNativeLibFolderPathForCurrentOS(),"Path does not equal property" + EXPECTED_PATH_PROPERTY);
            System.out.println("Path is as expected");
        }
    }
}
