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


import org.bboss.elasticsearchtest.springboot.crud.DocumentCRUD;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss开头
 * 对应的配置文件为application.properties文件
 * @author  yinbp [122054810@qq.com]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BBossESStarterTestCase {
	@Autowired
	private BBossESStarter bbossESStarter;
	@Autowired
	DocumentCRUD documentCRUD;

    @Test
    public void testBbossESStarter() throws Exception {
//        System.out.println(bbossESStarter);

		//验证环境,获取es状态
//		String response = serviceApiUtil.restClient().executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);

//		System.out.println(response);
		//判断索引类型是否存在，false表示不存在，正常返回true表示存在
		boolean exist = bbossESStarter.getRestClient().existIndiceType("twitter","tweet");

		//判读索引是否存在，false表示不存在，正常返回true表示存在
		exist =  bbossESStarter.getRestClient().existIndice("twitter");

		exist =  bbossESStarter.getRestClient().existIndice("agentinfo");

    }

	@Test
	public void testESSQLTranslate(){
		ClientInterface clientUtil = bbossESStarter.getRestClient();
		String dsl =  //将sql转换为dsl
				clientUtil.executeHttp("/_sql/_explain",//sql请求
						"select operModule.keyword from dbdemo group by operModule.keyword ",ClientInterface.HTTP_POST);//返回的文档封装对象类型

		//获取总记录数
		System.out.println(dsl);
	}

    @Test
	public void testCRUD() throws Exception {

		//删除/创建文档索引表
		documentCRUD.testCreateIndice();
		//添加/修改单个文档

		documentCRUD.testAddAndUpdateDocument();
		//批量添加文档
		documentCRUD.testBulkAddDocument();
		//检索文档
		documentCRUD.testSearch();
		//批量修改文档
		documentCRUD.testBulkUpdateDocument();

		//检索批量修改后的文档
		documentCRUD.testSearch();
		//带list复杂参数的文档检索操作
		documentCRUD.testSearchArray();
		//带from/size分页操作的文档检索操作
		documentCRUD.testPagineSearch();
		//带sourcefilter的文档检索操作
		documentCRUD.testSearchSourceFilter();

		documentCRUD.updateDemoIndice();
		documentCRUD.testBulkAddDocuments();
	}

	@Test
	public void testPerformaceCRUD() throws Exception {

		//删除/创建文档索引表
		documentCRUD.testCreateIndice();

		documentCRUD.testBulkAddDocuments();
	}

}
