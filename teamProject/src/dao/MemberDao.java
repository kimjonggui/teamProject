package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Member;

public class MemberDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDao() {
		con=JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}
	
	
	
	public int access(Member mb) {
		String sql="SELECT * FROM JOINMEMBER WHERE ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getNString("PW").equals(mb.getPw())) {
					return 1;
				}else {
					return 0;
				}
					
			}
			
		} catch (SQLException e) {
			System.out.println("로그인 예외처리");
			e.printStackTrace();
		}
		
		return -1;
	}

	public boolean memberjoin(Member mb) {
		String sql="INSERT INTO JOINMEMBER VALUES(?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			pstmt.setNString(2, mb.getPw());
			pstmt.setNString(3, mb.getName());
			pstmt.setNString(4, mb.getGender());
			int result=pstmt.executeUpdate();
			
			if(result!=0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("회원가입 예외처리");
			e.printStackTrace();
		}
		return false;
	}	

}
