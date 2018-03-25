package com.example.springdatarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestConfiguration {


    @Bean
    private RepositoryRestConfigurer repositoryRestConfigurerAdapter(){
        return new RepositoryRestConfigurerAdapter(){
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
              /*  config.setReturnBodyForPutAndPost(false);
                config.setBasePath("/api");
                config.setDefaultPageSize(3);*/
//                config.setPageParamName("newpage");
 //               config.setLimitParamName("newlimit");
  //              config.setSortParamName("newSort");
               // config.exposeIdsFor(Person.class);
            }
        };
    }
}
