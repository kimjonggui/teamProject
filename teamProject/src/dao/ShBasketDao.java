package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ShBasket;

public class ShBasketDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ShBasketDao() {
		con=JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public boolean basket(ShBasket sb) {
		String sql="INSERT INTO SHBASKET VALUES(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, sb.getSbid());
			pstmt.setNString(2, sb.getSbcode());
			pstmt.setNString(3, sb.getSbname());
			pstmt.setInt(4, sb.getSbqty());
			pstmt.setInt(5, sb.getSbprice());
			int result=pstmt.executeUpdate();
			
			if(result!=0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("basket 예외처리");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
