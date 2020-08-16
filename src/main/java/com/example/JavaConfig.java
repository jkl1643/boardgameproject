package com.example;

import com.example.Dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import custom_asking.CustomChange;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import MyGameRecord.MyGameRecordDao;
import custom_asking.CustomDao;
import custom_asking.CustomWrite;


@Configuration
public class JavaConfig {
    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/boardgameproject?characterEncoding=UTF-8&serverTimezone=UTC");
        ds.setUsername("boardgame");
        ds.setPassword("boardgame");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }


    @Autowired
    private CustomChange customchange;


    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
    
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao(), mygamerecordDao());
    }

    @Bean
    public checkIdPassword checkidpwd() {
        checkIdPassword checkidpwd = new checkIdPassword(dataSource());
        checkidpwd.setMemberDao(memberDao());
        return checkidpwd;
    }

    @Bean
    public MemberLogin lgn() {
        MemberLogin lgn = new MemberLogin(dataSource());
        lgn.setMemberDao(memberDao());
        return lgn;
    }

    @Bean
    public MemberLogout lgo() {
        MemberLogout lgo = new MemberLogout(dataSource());
        lgo.setMemberDao(memberDao());
        return lgo;
    }

    @Bean
    public ChangeInfoService changeInfoSvc(MemberDao memberDao) {
        ChangeInfoService changeInfoSvc = new ChangeInfoService();
        changeInfoSvc.setMemberDao(memberDao());
        return changeInfoSvc;
    }

    // 윤수명 추가22gf
    @Bean
    public CustomDao customdao() {
        return new CustomDao(dataSource());
    }

    @Bean
    public CustomWrite customwrite() {
        return new CustomWrite(customdao());
    }
    
    @Bean
    public MyGameRecordDao mygamerecordDao() {
        return new MyGameRecordDao(dataSource());
    }
//
    @Bean
    public CustomChange customchange() {
        CustomChange controller = new CustomChange();
        controller.setCustomChange(customdao());
        return controller;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public Main_Server Server()
    {
        return new Main_Server();
    }

    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }

    @Bean
    public SqlSessionFactory GameFactory() throws Exception
    {
        SqlSessionFactoryBean GameFactory = new SqlSessionFactoryBean();
        GameFactory.setDataSource(dataSource());
        GameFactory.setTypeAliases(Game.class);
        GameFactory.setMapperLocations(applicationContext.getResource("classpath:Mapper/GameMapper.xml"));
        return GameFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate GameFactoryTemplate() throws Exception
    {
        return new SqlSessionTemplate(GameFactory());

    }

    @Bean
    public DBcontroller dbcontrol() throws Exception
    {
        DBcontroller dbcontrol = new DBcontroller(GameFactoryTemplate().getMapper(GameDao.class));
        return dbcontrol;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
   	
   	
	

   
}