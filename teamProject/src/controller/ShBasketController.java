package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.shBasketMM;

@WebServlet({"/basket"})
public class ShBasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd= request.getServletPath();
		System.out.println("cmd="+cmd);
	
		ForwardInfo fi=null;
		shBasketMM sm=new shBasketMM(request, response);
		
		switch (cmd) {
		case ("/basket"):
			fi=sm.basket();
			break;

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
