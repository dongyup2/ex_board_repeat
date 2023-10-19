package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBcon;
import vo.Member;

public class MemberDaoImpl implements MemberDao{
	private DBcon db;
	
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	
	public MemberDaoImpl() {
		db = DBcon.getInstance();
	}
	
	@Override	
	public int loginCheck(String id, String pw) {
		int result = 0;
		
		sql = "SELECT COUNT(*) FROM tbl_member WHERE id=? AND pw=?";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			rs.next();
			
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int regMember(Member m) {
		int result = 0;
		sql = "INSERT INTO repeat_tbl_member VALUE (?,?,?,?,?)";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getId());
			pstmt.setString(3, m.getPw());
			pstmt.setString(4, m.getTel());
			pstmt.setString(5, m.getAddr());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
