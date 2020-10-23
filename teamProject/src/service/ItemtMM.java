package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ForwardInfo;
import bean.Itemlist;
import dao.ItemDao;

public class ItemtMM {
	HttpServletRequest request;
	HttpServletResponse response;
	
	public ItemtMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public ForwardInfo insertProduct() {
		Itemlist il=new Itemlist();
		ItemDao iDao=new ItemDao();
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
	      int size = 10 * 1024 * 1024;
	      File dir = new File(uploadPath);
	      if (!dir.exists()) {
	         dir.mkdir();
	      }
	      MultipartRequest mult=null;
		try {
			mult = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println("shebal");
			e.printStackTrace();
		}
	      HttpSession session =request.getSession();
		
		String ilkind=mult.getParameter("ilkind");
		String ilid=session.getAttribute("id").toString();
		String ilname=mult.getParameter("ilname");
		   int ilprice=Integer.parseInt(mult.getParameter("ilprice"));
		   int ilqty=Integer.parseInt(mult.getParameter("ilqty"));
		String ildetail=mult.getParameter("ildetail");
		String ilFILE= mult.getOriginalFileName("ilfile");
		
		il.setIlkind(ilkind);
		il.setIlid(ilid);
		il.setIlname(ilname);
		il.setIlprice(ilprice);
		il.setIlqty(ilqty);
		il.setIldetail(ildetail);
		il.setIlFILE(ilFILE);
		
		ForwardInfo fi=new ForwardInfo();
		
		boolean result=iDao.insertProduct(il);
		
		if(result) {
			fi.setPath("/loginFrm");
			fi.setRedirect(false);
		}else {
			fi.setPath("/ItemUpFrm");
			fi.setRedirect(false);
		}
		return fi;
	}

	public ForwardInfo getItemList(String string) {
		ItemDao iDao=new ItemDao();
		ForwardInfo fi=new ForwardInfo();
		
		List<Itemlist> pList=null;
		pList=iDao.getgetItemList(string);
		iDao.close();
		
		if(pList!=null) {
			request.setAttribute("pList", makehtml_pList(pList));
		}
			fi.setPath("main.jsp");
			fi.setRedirect(false);
		return fi;
	}

	private String makehtml_pList(List<Itemlist> pList) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<pList.size();i++) {
			Itemlist il=pList.get(i);
			
			sb.append("<div data-code="+il.getIlcode()+">");
			sb.append("<a target=\"_blank\" href='itemInfo?ilcode="+il.getIlcode()+"'>");
			sb.append("<img src='upload/"+il.getIlFILE()+"' width='240'; height: '100'>");
			sb.append("</a>");
			sb.append("<h3>"+il.getIlname()+"</h3>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	public ForwardInfo itemInfo() {
		Itemlist il=new Itemlist();
		ItemDao iDao=new ItemDao();
		String ilcode=request.getParameter("ilcode");
		il.setIlcode(ilcode);
		Itemlist info=iDao.itemInfo(il);
		
		ForwardInfo fi=new ForwardInfo();
		if(info!=null) {
			request.setAttribute("info", makeinfo(info));
		}
			fi.setPath("ItemList/itemInfo.jsp");
			fi.setRedirect(false);
		
		return fi;
	}

	private String makeinfo(Itemlist info) {
	      StringBuffer sb = new StringBuffer();
	      sb.append("<h2><b>올린 날짜: "+info.getIldate()+"</b></h2>");
	      sb.append("<h2><b>올린 사람: "+info.getIlname()+"</b></h2>");
	      sb.append("<h2><b>이름: "+info.getIlname()+"</b></h2>");
	      sb.append("<img src='upload/"+info.getIlFILE()+"' width='100px'>");
	      sb.append("<h2><b>가격: "+info.getIlprice()+"원</b></h2>");
	      sb.append("<h2><b>상세 정보: "+info.getIldetail()+"</b></h2>");
	      sb.append("<a href='basket?ilcode="+info.getIlcode()+"&ilname="+info.getIlname()
	      			+"&ilid="+info.getIlid()+"&ilprice="+info.getIlprice()+"&ilqty="+info.getIlqty()
	      			+"&ildetail="+info.getIldetail()+"&ildata="+info.getIldate()
	      			+"&ilfile"+info.getIlFILE()+"'>");
	      sb.append("<button>장바구니</button>");
	      sb.append("</a>");
		return sb.toString();
	}
   
}
