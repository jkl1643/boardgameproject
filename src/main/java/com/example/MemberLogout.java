package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberLogout {
	private MemberDao memberDao;
	private JdbcTemplate jdbcTemplate;
	public MemberLogout(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public void logout() {
		Member member = memberDao.selectByEmail(MemberLogin.loginEmail);
		System.out.println(MemberLogin.loginEmail + "님 로그아웃 되었습니다.");
		MainController.state = 0;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into LOGINLOG(EMAIL, STATUS, REGDATE) values(?, ?, ?)", new String[] { "ID" });
				pstmt.setString(1, MemberLogin.loginEmail);
				pstmt.setString(2, "Logout");
				pstmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId((int) keyValue.longValue());
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}