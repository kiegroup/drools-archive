/*
 * Copyright 2005 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.common;

import org.drools.core.RuleBaseConfiguration;
import org.drools.core.reteoo.AbstractTerminalNode;
import org.drools.core.reteoo.AsyncReceiveNode;
import org.drools.core.reteoo.AsyncSendNode;
import org.drools.core.reteoo.BetaNode;
import org.drools.core.reteoo.ConditionalBranchNode;
import org.drools.core.reteoo.FromNode;
import org.drools.core.reteoo.LeftInputAdapterNode;
import org.drools.core.reteoo.LeftTupleSink;
import org.drools.core.reteoo.ObjectTypeNode;
import org.drools.core.reteoo.QueryElementNode;
import org.drools.core.reteoo.QueryTerminalNode;
import org.drools.core.reteoo.RightInputAdapterNode;
import org.drools.core.reteoo.RuleTerminalNode;
import org.drools.core.reteoo.TimerNode;
import org.drools.core.reteoo.WindowNode;
import org.drools.core.rule.EvalCondition;

public interface MemoryFactory<T extends Memory> {

    // Avoid secondary super cache invalidation by testing for abstract classes first
    // Then interfaces
    // See: https://issues.redhat.com/browse/DROOLS-7521
    static MemoryFactory getTerminalNode(LeftTupleSink sink) {
        MemoryFactory terminalNode;
        if(sink instanceof RuleTerminalNode) {
            terminalNode = ((RuleTerminalNode) sink);
        } else if (sink instanceof QueryTerminalNode) {
            terminalNode = ((QueryTerminalNode) sink);
        } else {
            terminalNode = ((MemoryFactory) sink);
        }
        return terminalNode;
    }

    int getMemoryId();
    
    T createMemory(RuleBaseConfiguration config, InternalWorkingMemory wm);

    // Avoid secondary super cache invalidation by testing for abstract classes first
    // Then interfaces
    // See: https://issues.redhat.com/browse/DROOLS-7521
    static MemoryFactory getMemoryFactory(Object memoryFactory) {
        if (memoryFactory instanceof AsyncSendNode) {
            return (MemoryFactory) memoryFactory;
        } else if (memoryFactory instanceof AsyncReceiveNode) {
            return (MemoryFactory) memoryFactory;
        } else if (memoryFactory instanceof EvalCondition) {
            return (MemoryFactory) memoryFactory;
        } else if (memoryFactory instanceof ConditionalBranchNode) {
            return (ConditionalBranchNode) memoryFactory;
        } else if (memoryFactory instanceof FromNode) {
            return (FromNode) memoryFactory;
        } else if (memoryFactory instanceof LeftInputAdapterNode) {
            return (LeftInputAdapterNode) memoryFactory;
        } else if (memoryFactory instanceof QueryElementNode) {
            return (QueryElementNode) memoryFactory;
        } else if (memoryFactory instanceof TimerNode) {
            return (TimerNode) memoryFactory;
        } else if (memoryFactory instanceof WindowNode) {
            return (WindowNode) memoryFactory;
        } else if (memoryFactory instanceof BetaNode) {
            return (BetaNode) memoryFactory;
        } else if (memoryFactory instanceof RightInputAdapterNode) {
            return (RightInputAdapterNode) memoryFactory;
        } else if (memoryFactory instanceof ObjectTypeNode) {
            return (ObjectTypeNode) memoryFactory;
        } else if (memoryFactory instanceof AbstractTerminalNode) {
            return (AbstractTerminalNode) memoryFactory;
        } else {
            return (MemoryFactory) memoryFactory;
        }
    }
}
