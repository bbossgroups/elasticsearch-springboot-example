/*
 *  Copyright 2008-2019 bboss
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.esbboss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frameworkset.orm.annotation.Column;
import com.frameworkset.orm.annotation.ESId;
import org.frameworkset.elasticsearch.entity.ESBaseData;

import java.util.Date;

/**
 * Test entity, which can inherit the meta attribute from the ESBaseData object.
 * The meta attribute of the document will be set to the object instance during retrieval
 * @author yinbp[yin-bp@163.com]
 */
public class Demo extends ESBaseData {
	private Object dynamicPriceTemplate;
	//Set the document identity field
	@ESId(readSet = true,persistent = false)
	private Long demoId;
	private String contentbody;
	/**  When the date format is specified in the mapping definition,
	 *  the following two annotations need to be specified, for example:
	 *
	 "agentStarttime": {
	 "type": "date",
	 ###Specify multiple date formats
	 "format":"yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd'T'HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss||epoch_millis"
	 }
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	 @Column(dataformat = "yyyy-MM-dd HH:mm:ss.SSS")
	 */

	protected Date agentStarttime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(dataformat = "yyyy-MM-dd HH:mm:ss")
	private Date agentStarttimezh;
	private String applicationName;
	private String orderId;
//	@JsonProperty(value="contrast_status",access= JsonProperty.Access.WRITE_ONLY)
	private int contrastStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public String getContentbody() {
		return contentbody;
	}

	public void setContentbody(String contentbody) {
		this.contentbody = contentbody;
	}

	public Date getAgentStarttime() {
		return agentStarttime;
	}

	public void setAgentStarttime(Date agentStarttime) {
		this.agentStarttime = agentStarttime;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Long getDemoId() {
		return demoId;
	}

	public void setDemoId(Long demoId) {
		this.demoId = demoId;
	}

	public Object getDynamicPriceTemplate() {
		return dynamicPriceTemplate;
	}

	public void setDynamicPriceTemplate(Object dynamicPriceTemplate) {
		this.dynamicPriceTemplate = dynamicPriceTemplate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getContrastStatus() {
		return contrastStatus;
	}

	public void setContrastStatus(int contrastStatus) {
		this.contrastStatus = contrastStatus;
	}

	public Date getAgentStarttimezh() {
		return agentStarttimezh;
	}

	public void setAgentStarttimezh(Date agentStarttimezh) {
		this.agentStarttimezh = agentStarttimezh;
	}
}
