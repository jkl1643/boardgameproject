package com.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	public static int delaccount = 0;
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
		return count;
	}
	
	private RowMapper<Member> memRowMapper = 
			new RowMapper<Member>() {				
				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Member member = new Member(rs.getString("member_email"),
												rs.getString("member_password"),
												rs.getString("member_nickname"),
												rs.getInt("member_gamelog"),
												rs.getTimestamp("member_regdate")
												);
					member.setId((int) rs.getLong("ID"));
					return member;
				}
			};
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where member_email = ?", memRowMapper, email);
		return results.isEmpty() ? null : results.get(0);
	}
	/*public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
						Member member = new Member(rs.getString("EMAIL"),
												rs.getString("PASSWORD"),
												rs.getString("NAME"),
												rs.getString("TELEPHONE"),
												rs.getString("ADDRESS"),
												rs.getTimestamp("REGDATE"));
						member.setId(rs.getLong("ID"));
						return member;
					}
										
				}, email);
		return results.isEmpty() ? null : results.get(0);
	}*/
	
	
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(rs.getString("member_email"),
							rs.getString("member_password"),
							rs.getString("member_nickname"),
							rs.getInt("member_gamelog"),
							rs.getTimestamp("member_regdate")
					);
					member.setId((int) rs.getLong("ID"));
					return member;
				});
		return results;
	}
	
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set member_nickname = ?, member_password = ?, " + "where member_email = ?", member.getNickname(),
				member.getPassword(), member.getEmail());
	}

	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER(member_email, member_password, member_nickname, member_gamelog, member_regdate) values(?, ?, ?, ?, ?)", new String[] { "ID" });
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getNickname());
				pstmt.setString(4, String.valueOf(1));
				pstmt.setString(5, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId((int) keyValue.longValue());
	}
	
	public void delete(String email, String password) {
		jdbcTemplate.update("delete from MEMBER where member_email = ?", email);
		System.out.println(email + "님의 계정과 메모가 삭제 되었습니다.");
		delaccount = 1;
	}
	
	public List<Member> findpwd(String id, String tel) {
		System.out.println("findpwd안");
		return jdbcTemplate.query("select * from MEMBER WHERE member_email LIKE ? AND member_nickname LIKE ?",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(rs.getString("member_email"), rs.getString("member_password"), rs.getString("member_nickname")
							,rs.getInt("member_gamelog"), rs.getTimestamp("member_regdate"));
					member.setId((int) rs.getLong("ID"));
			return member;
		}, id, tel);
	}
}