package MyGameRecord;

import java.sql.*;


import java.time.LocalDateTime;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import MyGameRecord.MyGameRecord;


public class MyGameRecordDao {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<MyGameRecord> memRowMapper =
            new RowMapper<MyGameRecord>() {
                @Override
                public MyGameRecord mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    MyGameRecord record = new MyGameRecord
                    		(
                            rs.getInt("TOTAL"),
                            rs.getInt("WIN"),
                            rs.getInt("DRAW"),
                            rs.getInt("LOSE")
                            );
                    record.setGamerecord_number(rs.getLong("COUNT"));
                    return record;
                }
            };

    public MyGameRecordDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MyGameRecord selectByNickname(String nickname) {
        List<MyGameRecord> results = jdbcTemplate.query(
                "select * from GAMERECORD where NICKNAME = ?", // memo 수정
                memRowMapper, nickname);

        return results.isEmpty() ? null : results.get(0);
    }

    public MyGameRecord selectByMEMNUM(int mem_num) {
        List<MyGameRecord> results = jdbcTemplate.query(
                "select * from GAMERECORD where MEMBER_NUMBER = ?", // memo 수정
                memRowMapper, mem_num);

        return results.isEmpty() ? null : results.get(0);
    }
    
    /*
    public void insert(MyGameRecord record) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MYGAMERECORD (NICKNAME, TOTAL, WIN, DRAW, LOSE) " + // memo 수정
                                "values (?, ?, ?, ?, ?)",
                        new String[]{"COUNT"});

                pstmt.setString(1, record.getNickname());
                pstmt.setInt(2, record.getTotal());
                pstmt.setInt(3, record.getWin());
                pstmt.setInt(4, record.getDraw());
                pstmt.setInt(5, record.getLose());

                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        record.setCount(keyValue.longValue());
    }*/

    //
    public void update(MyGameRecord record) {
        jdbcTemplate.update(
                "update MYGAMERECORD set TOTAL = ?, WIN = ?, DRAW = ?, LOSE = ?, where MEMBER_NUMBER = ?",
                record.getTotal(), record.getWin(), record.getDraw(), record.getMember_number());
    }


    public List<MyGameRecord> selectAll() {
        List<MyGameRecord> results = jdbcTemplate.query("select * from CUSTOM",
                memRowMapper);
        return results;
    }


    //


    public MyGameRecord selectByCount(Long Count) {
        List<MyGameRecord> results = jdbcTemplate.query(
                "select * from MYGAMERECORD where COUNT = ?",
                memRowMapper, Count);

        return results.isEmpty() ? null : results.get(0);
    }
}