package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//jdbc common 
public class JdbcUtil {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // catch end
	}// static end

	public static Connection getConnection() { 
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sys as sysdba", "icia2020");
//			con.setAutoCommit(false);// 수동커밋
			System.out.println("오라클 접속 성공");
			// select, insert
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 접속 실패");
		} // catch end
		return con;
	}// connect end

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("CLOSE FAIL");
			e.printStackTrace();
		} // catch end
	}

	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit 성공");
		} catch (SQLException e) {
			System.out.println("commit 예외");
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback 성공");
		} catch (SQLException e) {
			System.out.println("rollback 예외");
			e.printStackTrace();
		}
	}
}
