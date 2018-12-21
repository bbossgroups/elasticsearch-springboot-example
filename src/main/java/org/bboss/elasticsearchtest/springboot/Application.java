package org.bboss.elasticsearchtest.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @author yinbp [122054810@qq.com]
 *
 */

@SpringBootApplication
//改变自动扫描的包
//@ComponentScan(basePackages = {"org.bboss.elasticsearchtest.springboot",
//        "org.frameworkset.elasticsearch.boot"})
public class Application {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }




}
