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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void testGetProperties() {
        final Properties props = new Properties();
        props.setProperty(
            "garbage.in",
            "out");
        final Properties allprops = Utils.getProperties(props);
        assertEquals(allprops.getProperty("garbage.in"), "out");
    }

    @Test
    void testSplitNull() {
        assertEquals(Collections.<String> emptyList(), Utils.splitClassNames(null, ","));
    }

    @Test
    void testSplitOmitEmptyLine() {
        List<String> clazzNames = Utils.splitClassNames("", ",");
        assertEquals(Collections.<String> emptyList(), clazzNames);

        clazzNames = Utils.splitClassNames("a,b", ",");
        assertEquals(Arrays.asList("a", "b"), clazzNames);
        clazzNames = Utils.splitClassNames("a,b,", ",");
        assertEquals(Arrays.asList("a", "b"), clazzNames);
        clazzNames = Utils.splitClassNames("a, b,", ",");
        assertEquals(Arrays.asList("a", "b"), clazzNames);
    }
}
