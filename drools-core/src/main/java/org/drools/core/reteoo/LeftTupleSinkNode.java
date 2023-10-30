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

package org.drools.core.reteoo;

import org.drools.core.rule.EvalCondition;

/**
 * Items placed in a <code>LinkedList<code> must implement this interface .
 * 
 * @see LeftTupleSinkNodeList
 */
public interface LeftTupleSinkNode
    extends
    LeftTupleSink {

    // Avoid secondary super cache invalidation by testing for abstract classes first
    // Then interfaces
    // See: https://issues.redhat.com/browse/DROOLS-7521
    static LeftTupleSinkNode getLeftTupleSinkNode(Sink sink) {
        if(sink instanceof QueryTerminalNode) {
            return (QueryTerminalNode) sink;
        } else if(sink instanceof AsyncSendNode) {
            return (AsyncSendNode) sink;
        } else if(sink instanceof EvalConditionNode) {
            return (EvalConditionNode) sink;
        } else if(sink instanceof RightInputAdapterNode) {
            return (RightInputAdapterNode) sink;
        } else if(sink instanceof AsyncReceiveNode) {
            return (AsyncReceiveNode) sink;
        } else if (sink instanceof ConditionalBranchNode) {
            return (ConditionalBranchNode) sink;
        } else if (sink instanceof FromNode) {
            return (FromNode) sink;
        } else if (sink instanceof QueryElementNode) {
            return (QueryElementNode) sink;
        } else if (sink instanceof TimerNode) {
            return (TimerNode) sink;
        } else if (sink instanceof BetaNode) {
            return (BetaNode) sink;
        }   else if (sink instanceof RightInputAdapterNode) {
            return (RightInputAdapterNode) sink;
        } else {
            return (LeftTupleSinkNode) sink;
        }
    }

    /**
     * Returns the next node
     * @return
     *      The next LinkedListNode
     */
    public LeftTupleSinkNode getNextLeftTupleSinkNode();

    /**
     * Sets the next node 
     * @param next
     *      The next LinkedListNode
     */
    public void setNextLeftTupleSinkNode(LeftTupleSinkNode next);

    /**
     * Returns the previous node
     * @return
     *      The previous LinkedListNode
     */
    public LeftTupleSinkNode getPreviousLeftTupleSinkNode();

    /**
     * Sets the previous node 
     * @param previous
     *      The previous LinkedListNode
     */
    public void setPreviousLeftTupleSinkNode(LeftTupleSinkNode previous);
}
