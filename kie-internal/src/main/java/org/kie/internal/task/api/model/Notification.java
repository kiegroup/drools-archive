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
package org.kie.internal.task.api.model;

import java.io.Externalizable;
import java.util.List;

import org.kie.api.task.model.I18NText;
import org.kie.api.task.model.OrganizationalEntity;

public interface Notification extends Externalizable  {

    Long getId();

    void setId(long id);

    NotificationType getNotificationType();

    List<I18NText> getDocumentation();

    void setDocumentation(List<I18NText> documentation);

    int getPriority();

    void setPriority(int priority);

    List<OrganizationalEntity> getRecipients();

    void setRecipients(List<OrganizationalEntity> recipients);

    List<OrganizationalEntity> getBusinessAdministrators();

    void setBusinessAdministrators(List<OrganizationalEntity> businessAdministrators);

    List<I18NText> getNames();

    void setNames(List<I18NText> names);

    List<I18NText> getSubjects();

    void setSubjects(List<I18NText> subjects);

    List<I18NText> getDescriptions();

    void setDescriptions(List<I18NText> descriptions);

}
