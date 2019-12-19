package org.bboss.elasticsearchtest.springboot;/*
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

import com.frameworkset.util.SimpleStringUtil;
import org.bboss.elasticsearchtest.springboot.script.ScriptImpl7;
import org.frameworkset.elasticsearch.serial.SerialUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptImpl7Test {
	@Autowired
	private ScriptImpl7 script;
	@Test
	public void test(){
		script.updateDocumentByScriptPath();
	}

	@Test
	public void test1(){
		script.updateDocumentByScriptQueryPath();
	}

	public static void main(String[] args){
		Map value = new HashMap();
		value.put("aaa","\r\n\"");
		String _value = SerialUtil.object2json(value);
		value.put("aaa",_value);
		_value = SerialUtil.object2json(value);
		System.out.println(_value);
		value = SimpleStringUtil.json2Object(_value,HashMap.class);
		System.out.println(_value);
	}
}
