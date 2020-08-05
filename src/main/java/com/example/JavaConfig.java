package com.example;

import org.apache.tomcat.jdbc.pool.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

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
        ds.setMinEvictableIdleTimeMillis(60000*3);
        ds.setTimeBetweenEvictionRunsMillis(10*1000);
        return ds;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
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
    
    // 윤수명 추가1
   	@Bean
   	public CustomDao customdao() {
   		return new CustomDao(dataSource());
   	}

   	@Bean
   	public CustomWrite boardrequeset() {
   		return new CustomWrite(customdao());
   	}
   	
   	@Bean
   	public PlatformTransactionManager transactionManager() {
   		DataSourceTransactionManager tm = new DataSourceTransactionManager();
   		tm.setDataSource(dataSource());
   		return tm;
   	}
    
    

}