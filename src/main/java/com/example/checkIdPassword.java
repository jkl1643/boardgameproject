package com.example;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class checkIdPassword {
    private MemberDao memberDao;
    private JdbcTemplate jdbcTemplate;

    public checkIdPassword(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void checkidpassword(String email, String pwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();
        member.checkPassword(email, pwd);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
