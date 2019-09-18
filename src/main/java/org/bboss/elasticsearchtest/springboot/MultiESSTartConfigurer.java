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

import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置和加载多个es集群实例,在application.yml配置文件中定义了两个集群default和logs
 * spring.elasticsearch.bboss.default
 * spring.elasticsearch.bboss.logs
 */
//@Configuration
//@Profile("multi-datasource")
@Configuration
public class MultiESSTartConfigurer {
	@Primary
	@Bean(initMethod = "start")
	@ConfigurationProperties("spring.elasticsearch.bboss.default")
	public BBossESStarter bbossESStarterDefault(){
		return new BBossESStarter();

	}

	@Bean(initMethod = "start")
	@ConfigurationProperties("spring.elasticsearch.bboss.logs")
	public BBossESStarter bbossESStarterLogs(){
		return new BBossESStarter();
	}
}
