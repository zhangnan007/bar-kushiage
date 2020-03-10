package com.bar.kushiage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bar.kushiage.dao")
@SpringBootApplication
public class KushiageApplication {

    public static Logger logger = LoggerFactory.getLogger(KushiageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KushiageApplication.class, args);
        logger.info("++++++++++++++++++++spring boot started++++++++++++++++++++");
    }

}
