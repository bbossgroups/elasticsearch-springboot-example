/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package org.bboss.elasticsearchtest.springboot;


import org.frameworkset.elasticsearch.client.ClientInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 多集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss.集群名称开头，例如：
 * spring.elasticsearch.bboss.default 默认es集群
 * spring.elasticsearch.bboss.logs  logs es集群
 * 两个集群通过 org.bboss.elasticsearchtest.springboot.MultiESSTartConfigurer加载
 * 对应的配置文件为application-multi-datasource.properties文件
 * @author yinbp [122054810@qq.com]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("multi-datasource")
public class MultiBBossESStartersTestCase {
	@Autowired
	private ServiceApiUtil serviceApiUtil;

    @Test
    public void testMultiBBossESStarters() throws Exception {

		//验证环境,获取es状态
		String response = serviceApiUtil.restClient().executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);
		System.out.println(response);


		//判断索引类型是否存在，false表示不存在，正常返回true表示存在
		boolean exist = serviceApiUtil.restClientLogs().existIndiceType("twitter","tweet");
		System.out.println("twitter/tweet:"+exist);
		//判读索引是否存在，false表示不存在，正常返回true表示存在
		exist = serviceApiUtil.restClientLogs().existIndice("twitter");
		System.out.println("twitter:"+exist);
		exist = serviceApiUtil.restClientLogs().existIndice("agentinfo");
		System.out.println("agentinfo:"+exist);
    }


}
