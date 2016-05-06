/* * Copyright (c) 2016 * <p> * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * <p>http://www.apache.org/licenses/LICENSE-2.0<p> * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.ecom.account.bootstrap;import org.apache.ibatis.session.SqlSessionFactory;import org.mybatis.spring.annotation.MapperScan;import org.springframework.context.annotation.*;import org.springframework.jdbc.datasource.DataSourceTransactionManager;import org.springframework.jdbc.datasource.DriverManagerDataSource;import org.mybatis.spring.SqlSessionFactoryBean;import org.springframework.transaction.annotation.EnableTransactionManagement;/** * @author jcordones13 * */@Configuration@MapperScan("com.ecom.account")@ComponentScan(basePackages = "com.ecom.account")@EnableTransactionManagement//@PropertySource(value = "classpath:application.properties")public class SpringAppConfig {    @Bean    public DriverManagerDataSource dataSource() {        DriverManagerDataSource dataSource = new DriverManagerDataSource();        dataSource.setDriverClassName("com.mysql.jdbc.Driver");        dataSource.setUrl("jdbc:mysql://localhost:3306/account_serv_db");        dataSource.setUsername("root");        dataSource.setPassword("root");        return dataSource;    }    @Bean    public SqlSessionFactory sqlSessionFactory() throws Exception {        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();        sessionFactory.setDataSource(dataSource());        //sessionFactory.setTypeAliasesPackage("com.ecom.account");        return sessionFactory.getObject();    }    @Bean    public DataSourceTransactionManager transactionManager() {        return new DataSourceTransactionManager(dataSource());    }}