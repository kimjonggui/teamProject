package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;
import bean.ShBasket;
import dao.ShBasketDao;

public class shBasketMM {
	HttpServletRequest request;
	HttpServletResponse response;
	public shBasketMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	public ForwardInfo basket() {
		ShBasketDao sDao=new ShBasketDao();
		ShBasket sb=new ShBasket();
		HttpSession session=request.getSession();
		String sbid=session.getAttribute("id").toString();
		String sbcode=request.getParameter("ilcode");
		String sbname=request.getParameter("ilname");
		   int sbqyt=Integer.parseInt(request.getParameter("ilqty"));
		   int sbprice=Integer.parseInt(request.getParameter("ilprice"));
		sb.setSbid(sbid);
		sb.setSbcode(sbcode);
		sb.setSbname(sbname);
		sb.setSbqty(sbqyt);
		sb.setSbprice(sbprice);
		
		ForwardInfo fi=new ForwardInfo();
		boolean result=sDao.basket(sb);
		if(result) {
			fi.setPath("/sdf");
			fi.setRedirect(false);
		}else {
			fi.setPath("/fds");
			fi.setRedirect(false);
		}
		
		return fi;
	}
	
	
	
}
