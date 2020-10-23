package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Itemlist;

public class ItemDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ItemDao() {
		con=JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}
	public boolean insertProduct(Itemlist il) {
		String sql="INSERT INTO ITEMLIST VALUES(?||LPAD(il_SEQ.NEXTVAL,4,0),?,?,?,?,?,DEFAULT,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, il.getIlkind());
			pstmt.setNString(2, il.getIlid());
			pstmt.setNString(3, il.getIlname());
			pstmt.setInt(4, il.getIlprice());
			pstmt.setInt(5, il.getIlqty());
			pstmt.setNString(6, il.getIldetail());
			pstmt.setNString(7, il.getIlFILE());
			int result = pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}	
		} catch (SQLException e) {
			System.out.println("상품 등록 예외처리");
			e.printStackTrace();
		}
		
		return false;
	}

	public List<Itemlist> getgetItemList(String kind) {
String sql="SELECT * FROM ITEMLIST WHERE ILCODE LIKE '%'||?||'%'";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, kind);
			rs=pstmt.executeQuery();
			List<Itemlist> pList=new ArrayList<Itemlist>();
			while (rs.next()) {
				Itemlist li=new Itemlist();
				li.setIlcode(rs.getNString("ILCODE"));
				li.setIlFILE(rs.getNString("ILFILE"));
				li.setIlname(rs.getNString("ILNAME"));
				pList.add(li);
			}
				return pList;
			
		} catch (SQLException e) {
			System.out.println("getList Dao 예외처리");
			e.printStackTrace();
		}
		return null;
	}

	public Itemlist itemInfo(Itemlist il) {
		String sql="SELECT * FROM ITEMLIST WHERE ILCODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, il.getIlcode());
			rs=pstmt.executeQuery();
			
			Itemlist ilt=new Itemlist();
			while (rs.next()) {
				
				ilt.setIlcode(rs.getNString("ILCODE"));
				ilt.setIlname(rs.getNString("ILNAME"));
				ilt.setIlprice(rs.getInt("ILPRICE"));
				ilt.setIlqty(rs.getInt("ILQTY"));
				ilt.setIldate(rs.getNString("ILDATE"));
				ilt.setIlFILE(rs.getNString("ILFILE"));
				ilt.setIldetail(rs.getNString("ILDETAIL"));
				ilt.setIlid(rs.getNString("ILID"));
			}
				return ilt;
		} catch (SQLException e) {
			System.out.println("상세정보 예외처리");
			e.printStackTrace();
		}
		return null;
	}

}
