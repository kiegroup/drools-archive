/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.drools.testcoverage.regression;

import java.util.Collection;

import org.drools.testcoverage.common.util.KieBaseTestConfiguration;
import org.drools.testcoverage.common.util.KieBaseUtil;
import org.drools.testcoverage.common.util.KieUtil;
import org.drools.testcoverage.common.util.TestConstants;
import org.drools.testcoverage.common.util.TestParametersUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kie.api.KieBase;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.Message.Level;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class NonStringCompareTest {

    private static final String genericDrl =
            "package " + TestConstants.PACKAGE_REGRESSION + "\n"
            + "declare Fact\n"
            + "    field : String\n"
            + "end\n"
            + "rule generic\n"
            + "    when\n"
            + "       Fact( field == %s )\n"
            + "    then\n"
            + "       // consequence\n"
            + "end\n";

    private final KieBaseTestConfiguration kieBaseTestConfiguration;

    public NonStringCompareTest(final KieBaseTestConfiguration kieBaseTestConfiguration) {
        this.kieBaseTestConfiguration = kieBaseTestConfiguration;
    }

    @Parameterized.Parameters(name = "KieBase type={0}")
    public static Collection<Object[]> getParameters() {
        return TestParametersUtil.getKieBaseConfigurations();
    }

    @Test
    public void testStringCompare() throws Exception {
        testScenario("\"someString\"", "someString");
    }

    @Test
    public void testNonQuotedStringComapre() {
        final KieBuilder kbuilder = build("someString");
        assertThat(kbuilder.getResults().getMessages(Level.ERROR).size()).isEqualTo(1);
    }

    @Test
    public void testIntCompare() throws Exception {
        testScenario("13", "13");
    }

    private void testScenario(final String factFieldValueForDrl, final String factFieldValueForTest)
            throws IllegalAccessException, InstantiationException {

        final KieBuilder kbuilder = build(factFieldValueForDrl);
        assertThat(kbuilder.getResults().getMessages(Level.ERROR)).isEmpty();

        final KieBase kbase = KieBaseUtil.getDefaultKieBaseFromKieBuilder(kbuilder);
        final KieSession ksession = kbase.newKieSession();

        try {
            final FactType type = kbase.getFactType(TestConstants.PACKAGE_REGRESSION, "Fact");
            final Object fact = type.newInstance();
            type.set(fact, "field", factFieldValueForTest);

            ksession.insert(fact);
            final int count = ksession.fireAllRules();
            assertThat(count).isEqualTo(1);
        } finally {
            ksession.dispose();
        }
    }

    private KieBuilder build(final String replacement) {
        final String drl = String.format(genericDrl, replacement);
        return KieUtil.getKieBuilderFromDrls(kieBaseTestConfiguration, false, drl);
    }
}
