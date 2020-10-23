package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.MemberMM;

@WebServlet({"/index","/logout","/joinFrm","/loginFrm","/access","/memberjoin"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getServletPath();
		System.out.println("cmd="+cmd);
		
		ForwardInfo fi=new ForwardInfo();
		MemberMM mm=new MemberMM(request, response);
		
		switch (cmd) {
		case ("/access"):
			fi=mm.access();
			break;
		case ("/loginFrm"):
			fi=new ForwardInfo();
			fi.setPath("loginFrm.jsp");
			fi.setRedirect(false);
			break;
		case ("/memberjoin"):
			fi=mm.memberjoin();
			break;
		case ("/joinFrm"):
			fi=new ForwardInfo();
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			break;
		case ("/index"):
			fi=new ForwardInfo();
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			break;
		case ("/logout"):
			fi=mm.logout();
		default:
			break;
		}
		if(fi!=null) {
			if(fi.isRedirect()) {
				response.sendRedirect(fi.getPath());
			}else {
				request.getRequestDispatcher(fi.getPath()).forward(request, response);
			}
		}
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
