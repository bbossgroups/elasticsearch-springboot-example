package org.bboss.elasticsearchtest.springboot;
/*
 *  Copyright 2008 biaoping.yin
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

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.stereotype.Service;

/**
 * 管理es rest client组件实例
 */
@Service
public class ServiceApiUtil {


	/**
	 * 获取操作默认的es集群的客户端工具组件
	 * @return
	 */
	public ClientInterface restClient(){
		return  ElasticSearchHelper.getRestClientUtil();
	}

	/**
	 * 获取操作默认的es集群的加载dsl配置文件的客户端工具组件
	 * @return
	 */
	public ClientInterface restDemoConfigClient(){
		return  ElasticSearchHelper.getConfigRestClientUtil("esmapper/demo.xml");
	}

	/**
	 * 获取操作logs的es集群的客户端工具组件
	 * @return
	 */
	public ClientInterface restClientLogs(){
		return  ElasticSearchHelper.getRestClientUtil("logs");
	}
	/**
	 * 获取操作logs的es集群的加载dsl配置文件的客户端工具组件
	 * @return
	 */
	public ClientInterface restConfigClientLogs(){
		return  ElasticSearchHelper.getConfigRestClientUtil("logs","esmapper/demo.xml");
	}
}
