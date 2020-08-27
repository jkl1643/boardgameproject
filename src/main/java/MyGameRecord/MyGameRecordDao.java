package MyGameRecord;

import java.nio.file.FileStore;
import java.sql.*;


import java.time.LocalDateTime;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.example.MemberDao;
import com.example.RegisterRequest;
import org.apache.catalina.Session;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.example.Member;
import com.example.Dao.Game;

import MyGameRecord.MyGameRecord;


public class MyGameRecordDao {
    private MemberDao memberDao;
	private Game game;
    private JdbcTemplate jdbcTemplate;
    public MyGameRecordDao(DataSource dataSource, MemberDao memberDao) {
        this.memberDao = memberDao;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<MyGameRecord> memRowMapper =
            new RowMapper<MyGameRecord>() {
                @Override
                public MyGameRecord mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    MyGameRecord record = new MyGameRecord
                    		(
                            rs.getInt("GAMERECORD_TOTAL"),
                            rs.getInt("GAMERECORD_WIN"),
                            rs.getInt("GAMERECORD_DRAW"),
                            rs.getInt("GAMERECORD_LOSE"),
                            rs.getInt("GAME_NUMBER"),
                            rs.getLong("MEMBER_NUMBER")
                            );
                    record.setGamerecord_number(rs.getLong("GAMERECORD_NUMBER"));
                    return record;
                }
            };

    public MyGameRecord selectByMEMNUM(Long mem_num) {
        List<MyGameRecord> results = jdbcTemplate.query(
                "select * from GAMERECORD where MEMBER_NUMBER = ?", // memo 수정
                memRowMapper, mem_num);

        return results.isEmpty() ? null : results.get(0);
    }
    
    
    public void insert(MyGameRecord record) { // 1차 실험용
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into GAMERECORD ( GAMERECORD_TOTAL, GAMERECORD_WIN, GAMERECORD_DRAW, "
                        + "GAMERECORD_LOSE, GAME_NUMBER, MEMBER_NUMBER) " + // memo 수정
                                "values (?, ?, ?, ?, ?,?)",
                        new String[]{"GAMERECORD_NUMBER"});

                pstmt.setInt(1, record.getTotal());
                pstmt.setInt(2, record.getWin());
                pstmt.setInt(3, record.getDraw());
                pstmt.setInt(4, record.getLose());
                pstmt.setInt(5, record.getGame_number());
                pstmt.setLong(6, record.getMember_number());
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        record.setGamerecord_number(keyValue.longValue());
    }


    public void insert2(MyGameRecord record, HttpSession session, RegisterRequest req) { //2차 실험용
        Member member3 = memberDao.selectByEmail(req.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into GAMERECORD ( GAMERECORD_TOTAL, GAMERECORD_WIN, GAMERECORD_DRAW, "
                                + "GAMERECORD_LOSE, GAME_NUMBER, MEMBER_NUMBER) " + // memo 수정
                                "values (?, ?, ?, ?, ?,?)",
                        new String[]{"GAMERECORD_NUMBER"});

                pstmt.setInt(1, record.getTotal());
                pstmt.setInt(2, record.getWin());
                pstmt.setInt(3, record.getDraw());
                pstmt.setInt(4, record.getLose());
                pstmt.setInt(5, 1);
                pstmt.setLong(6, member3.getId());
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        record.setGamerecord_number(keyValue.longValue());
    }

    public void update(MyGameRecord record) {
        jdbcTemplate.update(
                "update GAMERECORD set GAMERECORD_TOTAL = ?, GAMERECORD_WIN = ?, GAMERECORD_DRAW = ?, GAMERECORD_LOSE = ? where MEMBER_NUMBER LIKE ?",
                record.getTotal(), record.getWin(), record.getDraw(), record.getLose(), record.getMember_number());
    }

    public List<MyGameRecord> selectAll() {
        List<MyGameRecord> results = jdbcTemplate.query("select * from GAMERECORD",
                memRowMapper);
        return results;
    }

    public MyGameRecord selectByGamenumber(Long Count) {
        List<MyGameRecord> results = jdbcTemplate.query(
                "select * from GAMERECORD where GAMERECORD_NUMBER = ?",
                memRowMapper, Count);
        return results.isEmpty() ? null : results.get(0);
    }
    
    
    public void insert1(int total, int win, int lose, int draw, int game_number, int mem_number) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into GAMERECORD ( TOTAL, WIN, DRAW, LOSE, GAME_NUMBER, MEMBER_NUMBER) " + // memo 수정
                                "values (?, ?, ?, ?, ?,?)",
                        new String[]{"COUNT"});

                pstmt.setInt(1, total);
                pstmt.setInt(2, win);
                pstmt.setInt(3, lose);
                pstmt.setInt(4, draw);
                pstmt.setInt(5, game_number);
                pstmt.setInt(6, mem_number);
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
    }
    
    
    
    
    
    
    
    
    
    
    
}