package org.bboss.elasticsearchtest.springboot;


import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    @Bean
    public ClientInterface restClient(){
        return  ElasticSearchHelper.getRestClientUtil();
    }
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);




    }




}
