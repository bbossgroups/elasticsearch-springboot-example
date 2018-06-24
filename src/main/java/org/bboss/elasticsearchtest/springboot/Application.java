package org.bboss.elasticsearchtest.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @author yinbp [122054810@qq.com]
 * EnableAutoConfiguration 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
 */

@SpringBootApplication

public class Application {

    private Logger logger = LoggerFactory.getLogger(Application.class);
//
//	@Autowired
//	private BBossESStarter bbossESStarter;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);




    }


//    public boolean initEsConfig() {
//        String envStr = env.getProperty("application.env");
//        logger.info("ElasticSearch init properties,env:{}",envStr);
//
//            ElasticSearchBoot.boot("application-"+envStr+".properties");
//
//        return true;
//    }
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        initEsConfig();
//    }


}
