package com.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemoDao {	
	static long nextId2 = 0;
	
	private JdbcTemplate jdbcTemplate;
	public MemoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMO", Integer.class);
		return count;
	}
	public Memo selectById(Long id) {
		List<Memo> results = jdbcTemplate.query("select * from MEMO where ID = ?",
				new RowMapper<Memo>() {
					@Override
					public Memo mapRow(ResultSet rs, int rowNum) throws SQLException{
						Memo memo = new Memo(rs.getString("EMAIL"),
												rs.getString("YEAR"),
												rs.getString("MONTH"),
												rs.getString("DAY"),
												rs.getString("MEMO"),
												rs.getString("IMAGEPATH"));
						memo.setId(rs.getLong("ID"));
						return memo;
					}
										
				}, id);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Memo> selectAll(){
		List<Memo> results = jdbcTemplate.query("select * from MEMO",
				(ResultSet rs, int rowNum) -> {
					Memo memo = new Memo(rs.getString("EMAIL"),
											rs.getString("YEAR"),
											rs.getString("MONTH"),
											rs.getString("DAY"),
											rs.getString("MEMO"),
											rs.getString("IMAGEPATH"));
					memo.setId(rs.getLong("ID"));
					return memo;
				});
		return results;
	}
	
	public void update(Memo memo) {
		jdbcTemplate.update(
				"update MEMO set MEMO = ?, IMAGEPATH = ?" + "where EMAIL = ? AND YEAR = ? AND MONTH = ? AND DAY = ?",
				memo.getMemo(), memo.getSaveImagePath(), memo.getEmail(), memo.getYear(), memo.getMonth(), memo.getDay());
	}

	public void insert(final Memo memo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMO(EMAIL, YEAR, MONTH, DAY, MEMO, IMAGEPATH, REGDATE) values(?, ?, ?, ?, ?, ?, ?)", new String[] { "ID" });
				pstmt.setString(1, memo.getEmail());
				pstmt.setString(2, memo.getYear());
				pstmt.setString(3, memo.getMonth());
				pstmt.setString(4, memo.getDay());
				pstmt.setString(5, memo.getMemo());
				pstmt.setString(6, memo.getSaveImagePath());
				pstmt.setString(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		memo.setId(keyValue.longValue());
	}
	
	public void delete(String year, String month, String day) {
		int loopSize = count();
		if (loopSize == 0) {
			System.out.println(MemberLogin.loginEmail + "님의 " + year + "년 " + month + "월 " + day + "에 등록되어있는 메모가 없습니다.");
			return;
		} else {
			Memo memoId;
			for (int i = 1; i < 100; i++) {
				memoId = selectById(Long.valueOf(i));
				if (memoId == null)
					continue;				
				if (memoId.getEmail().equals(MemberLogin.loginEmail) && memoId.getYear().equals(year)
						&& memoId.getMonth().equals(month) && memoId.getDay().equals(day)) {
					jdbcTemplate.update("delete from MEMO where Email = ? AND Year = ? AND Month = ? AND DAY = ?", MemberLogin.loginEmail, year, month, day);					
					System.out.println(MemberLogin.loginEmail + "님의 " + year + "년 " + month + "월 " + day + "일의 메모가 삭제 되었습니다.");
					break;
				}
				System.out.println(MemberLogin.loginEmail + "님의 " + year + "년 " + month + "월 " + day + "에 등록되어있는 메모가 없습니다.");
			}			
		}
	}
	
	public List<Memo> search(String keyword) {
		Object[] params = { "%" + keyword + "%" };
		return jdbcTemplate.query("select * from MEMO WHERE MEMO LIKE ?",
				(ResultSet rs, int rowNum) -> {
					Memo memo = new Memo(rs.getString("EMAIL"), rs.getString("YEAR"), rs.getString("MONTH"),
					rs.getString("DAY"), rs.getString("MEMO"), rs.getString("IMAGEPATH"));
			memo.setId(rs.getLong("ID"));
			return memo;
		}, params);
	}
}
