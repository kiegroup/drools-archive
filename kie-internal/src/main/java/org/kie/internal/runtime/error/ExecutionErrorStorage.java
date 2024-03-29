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
package org.kie.internal.runtime.error;

import java.util.List;

public interface ExecutionErrorStorage {

    ExecutionError store(ExecutionError error);
    
    ExecutionError get(String errorId);
    
    void acknowledge(String user, String... errorId);
    
    List<ExecutionError> list(Integer page, Integer pageSize);
    
    List<ExecutionError> listByProcessInstance(String processInstanceId, Integer page, Integer pageSize);
    
    List<ExecutionError> listByActivity(String activityName, Integer page, Integer pageSize);
    
    List<ExecutionError> listByDeployment(String deploymentId, Integer page, Integer pageSize);
}
