package custom_asking;

import java.sql.*;

import java.time.LocalDateTime;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import custom_asking.Custom;;

public class CustomDao {	
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Custom> memRowMapper = 
			new RowMapper<Custom>() {
				@Override
				public Custom mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Custom custom = new Custom(rs.getString("TITLE"),
							rs.getString("CONTENT"),
							rs.getTimestamp("REGDATE").toLocalDateTime(),
							rs.getInt("MEMBER_NUMBER")
						);
					custom.setCount(rs.getLong("COUNT"));
					return custom;
				}
			};

	public CustomDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Custom selectByTitle(String title) {
		List<Custom> results = jdbcTemplate.query(
				"select * from CUSTOM where TITLE = ?", // memo 수정
				memRowMapper, title);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public Custom selectByNumber(int number) {
		List<Custom> results = jdbcTemplate.query(
				"select * from CUSTOM where MEMBER_NUMBER = ?", // memo 수정
				memRowMapper, number);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Custom custom) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
			
				PreparedStatement pstmt = con.prepareStatement(
						"insert into CUSTOM (TITLE, CONTENT,  REGDATE, MEMBER_NUMBER) " + 
						"values (?, ?, ?, ? )",
						
						new String[] { "COUNT" });
		
				pstmt.setString(1, custom.getTitle());
				pstmt.setString(2, custom.getContent());
				pstmt.setTimestamp(3,
						Timestamp.valueOf(custom.getRegisterDateTime()));
				pstmt.setInt(4, custom.getNumber());
	
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		custom.setCount(keyValue.longValue());
	}

	public void update(Custom custom) {
		jdbcTemplate.update(
				"update CUSTOM set TITLE = ?, CONTENT = ? where COUNT = ?",
				custom.getTitle(), custom.getContent(),  custom.getCount());
	}

	
	public List<Custom> selectAll() {
		List<Custom> results = jdbcTemplate.query("select * from CUSTOM",
				memRowMapper);
		return results;
	}
	
	public void delete(Custom custom) {
		jdbcTemplate.update(
				"delete from CUSTOM where COUNT = ?", // memo 수정
				custom.getCount());
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from CUSTOM", Integer.class); 
		return count;
	}

	public List<Custom> selectByRegdate(LocalDateTime from, LocalDateTime to) {
		List<Custom> results = jdbcTemplate.query(
				"select * from CUSTOM where REGDATE between ? and ? " +
						"order by REGDATE desc",
				memRowMapper,
				from, to);
		return results;
	}

	public Custom selectByCount(Long Count) {
		List<Custom> results = jdbcTemplate.query(
				"select * from CUSTOM where COUNT = ?",
				memRowMapper, Count);

		return results.isEmpty() ? null : results.get(0);
	}
}