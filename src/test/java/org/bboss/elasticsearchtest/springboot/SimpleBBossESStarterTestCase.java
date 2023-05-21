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


import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.MetaMap;
import org.frameworkset.elasticsearch.entity.PitId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss开头
 * 对应的配置文件为application.properties文件
 * @author  yinbp [122054810@qq.com]
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleBBossESStarterTestCase {
	private static Logger logger = LoggerFactory.getLogger(SimpleBBossESStarterTestCase.class);
	@Autowired
	private BBossESStarter bbossESStarter;


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
    public void testPitIdSearchAfter(){
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient("esmapper/demo7.xml");
        //申请pitid
        PitId pitId = clientUtil.requestPitId("dbdemofull","1m");
        logger.info("pitId.getId() {}",pitId.getId());
        Map params = new HashMap();
        params.put("size",100);
        params.put("user","admin");
        String pid = pitId.getId();

        String prePid = null;
        List<String> pids = new ArrayList<>();
        pids.add(pid);
        do {

            params.put("pid",pid);
            prePid = pid;
            ESDatas<MetaMap> datas = clientUtil.searchList("/_search", "queryPidSearchAfter", params, MetaMap.class);
            pid = datas.getPitId();
            pids.add(pid);
            List<MetaMap> metaMaps = datas.getDatas();
            if(metaMaps != null && metaMaps.size() > 0 ){
                MetaMap metaMap = metaMaps.get(metaMaps.size() - 1);
                Object[] sort = metaMap.getSort();
                params.put("timestamp",sort[0]);
                params.put("_shard_doc",sort[1]);
            }

            if(metaMaps.size() < 100){
                //达到最后一页，分页查询结束
                break;
            }
            logger.info("datas.getPitId() {}", pid);
           // logger.info(clientUtil.deletePitId(prePid));

        }while (true);
        String pre = null;
        for(int i =0 ; i < pids.size(); i ++){
            if(pre == null)
                pre = pids.get(i);
            else{
                System.out.println("pre:"+pre + "\r\n" + "now:"+pids.get(i)  + "\r\n" + "now equals pre:"+pre.equals(pids.get(i)) );
                pre = pids.get(i);
            }
            logger.info(clientUtil.deletePitId(pids.get(i)));
        }

    }


}
