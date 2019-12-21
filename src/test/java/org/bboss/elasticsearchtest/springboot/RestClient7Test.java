package org.bboss.elasticsearchtest.springboot;
/**
 * Copyright 2008 biaoping.yin
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.example.esbboss.entity.Demo;
import com.example.esbboss.entity.DemoSearchResult;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p></p>
 * <p>Copyright (c) 2018</p>
 * @Date 2019/9/18 10:27
 * @author biaoping.yin
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClient7Test {
	private Logger logger = LoggerFactory.getLogger(RestClient7Test.class);
	@Autowired
	private BBossESStarter bbossESStarter;
	//DSL config file path
	private String mappath = "esmapper/demo7.xml";
	@Test
	public void testException(){
		ClientInterface clientUtil = bbossESStarter.getRestClient();
		try {
			clientUtil.getDocument("demo", (String) null);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testException1(){
		ClientInterface clientUtil = bbossESStarter.getRestClient();

		try {
			clientUtil.getDocument("demo","");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void test(){
		this.dropAndCreateAndGetIndice();
		addAndUpdateDocument();
		searchAllPararrel();
		this.search();
		this.deleteDocuments();
	}
	public void dropAndCreateAndGetIndice(){
		//Create a client tool to load configuration files, single instance multithreaded security
		ClientInterface clientUtil = bbossESStarter.getConfigRestClient(mappath);
		try {
			//To determine whether the indice demo exists, it returns true if it exists and false if it does not
			boolean exist = clientUtil.existIndice("demo");

			//Delete mapping if the indice demo already exists
			if(exist) {
				String r = clientUtil.dropIndice("demo");
				logger.debug("clientUtil.dropIndice(\"demo\") response:"+r);

			}
			//Create index demo
			clientUtil.createIndiceMapping("demo",//The indice name
					"createDemoIndice");//Index mapping DSL script name, defined createDemoIndice in esmapper/demo.xml

			String demoIndice = clientUtil.getIndice("demo");//Gets the newly created indice structure
			logger.info("after createIndiceMapping clientUtil.getIndice(\"demo\") response:"+demoIndice);
		} catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void addAndUpdateDocument()  {
		//Build a create/modify/get/delete document client object, single instance multi-thread security
		ClientInterface clientUtil = bbossESStarter.getRestClient();
		//Build an object as index document
		Demo demo = new Demo();
		demo.setDemoId(2l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is content body2");
		demo.setName("liudehua");
		demo.setOrderId("NFZF15045871807281445364228");
		demo.setContrastStatus(2);


		//Add the document and force refresh
		String response = clientUtil.addDocument("demo",//indice name
				demo,"refresh=true");



		logger.debug("Print the result：addDocument-------------------------");
		logger.debug(response);

		demo = new Demo();
		demo.setDemoId(3l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo3");
		demo.setContentbody("this is content body3");
		demo.setName("zhangxueyou");
		demo.setOrderId("NFZF15045871807281445364228");
		demo.setContrastStatus(3);
		demo.setAgentStarttime(new Date());
		demo.setAgentStarttimezh(new Date());

		//Add the document and force refresh
		response = clientUtil.addDocument("demo",//indice name
				demo,"refresh=true");

		//Get the document object according to the document id, and return the Demo object
		demo = clientUtil.getDocument("demo",//indice name
				"2",//document id
				Demo.class);

		//update document
		demo = new Demo();
		demo.setDemoId(2l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
		demo.setAgentStarttime(new Date());
		demo.setApplicationName("blackcatdemo2");
		demo.setContentbody("this is modify content body2");
		demo.setName("刘德华modify\t");
		demo.setOrderId("NFZF15045871807281445364228");
		demo.setContrastStatus(2);
		demo.setAgentStarttimezh(new Date());
		//Execute update and force refresh
		response = clientUtil.addDocument("demo",//index name
				demo,"refresh=true");


		//Get the modified document object according to the document id and return the json message string
		response = clientUtil.getDocument("demo",//indice name
				"2");//document id
		logger.debug("Print the modified result:getDocument-------------------------");
		logger.debug(response);




		logger.debug("Print the modified result：getDocument-------------------------");
		logger.debug(response);


	}

	public void deleteDocuments(){
		//Build a create/modify/get/delete document client object, single instance multi-thread security
		ClientInterface clientUtil = bbossESStarter.getRestClient();
		//Batch delete documents
		clientUtil.deleteDocuments("demo",//indice name
				new String[]{"2","3"});//Batch delete document ids
	}

	/**
	 * Use slice parallel scoll query all documents of indice demo by 2 thread tasks. DEFAULT_FETCHSIZE is 5000
	 */
	public void searchAllPararrel(){
		ClientInterface clientUtil = bbossESStarter.getRestClient();
		ESDatas<Demo> esDatas = clientUtil.searchAllParallel("demo", Demo.class,2);
	}



	/**
	 * Search the documents
	 */
	public DemoSearchResult search()   {
		//Create a load DSL file client instance to retrieve documents, single instance multithread security
		ClientInterface clientUtil = bbossESStarter.getConfigRestClient(mappath);
		//Set query conditions, pass variable parameter values via map,key for variable names in DSL
		//There are four variables in the DSL:
		//        applicationName1
		//        applicationName2
		//        startTime
		//        endTime
		Map<String,Object> params = new HashMap<String,Object>();
		//Set the values of applicationName1 and applicationName2 variables
		params.put("applicationName1","blackcatdemo2");
		params.put("applicationName2","blackcatdemo3");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Set the time range, and accept the long value as the time parameter
		try {
			params.put("startTime",dateFormat.parse("2017-09-02 00:00:00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		params.put("endTime",new Date().getTime());


		//Execute the query
		ESDatas<Demo> esDatas =  //ESDatas contains a collection of currently retrieved records, up to 1000 records, specified by the size attribute in the DSL
				clientUtil.searchList("demo/_search",//demo as the indice, _search as the search action
						"searchDatas",//DSL statement name defined in esmapper/demo.xml
						params,//Query parameters
						Demo.class);//Data object type Demo returned


		//Gets a list of result objects and returns max up to 1000 records (specified in DSL)
		List<Demo> demos = esDatas.getDatas();

//		String json = clientUtil.executeRequest("demo/_search",//demo as the index table, _search as the search action
//				"searchDatas",//DSL statement name defined in esmapper/demo.xml
//				params);//Query parameters

//		String json = com.frameworkset.util.SimpleStringUtil.object2json(demos);
		//Gets the total number of records
		long totalSize = esDatas.getTotalSize();
		DemoSearchResult demoSearchResult = new DemoSearchResult();
		demoSearchResult.setDemos(demos);
		demoSearchResult.setTotalSize(totalSize);
		return demoSearchResult;
	}
}
