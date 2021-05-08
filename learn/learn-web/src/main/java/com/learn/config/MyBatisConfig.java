package com.learn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.learn.mapper","com.learn.dao"})
public class MyBatisConfig {
}
