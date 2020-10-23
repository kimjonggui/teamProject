package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;
import bean.Member;
import dao.MemberDao;

public class MemberMM {
	HttpServletRequest request;
	HttpServletResponse response;

	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo access() {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);

		Member mb = new Member();
		MemberDao mDao = new MemberDao();
		mb.setId(id);
		mb.setPw(pw);

		ForwardInfo fi = new ForwardInfo();
		int result = mDao.access(mb);

		switch (result) {
		case -1:
			request.setAttribute("msgAccess", "id가 없어요!");
			fi.setPath("/index.jsp");
			fi.setRedirect(false);
			break;
		case 0:
			request.setAttribute("msgAccess", "pw가 틀렸습니다!");
			fi.setPath("/index.jsp");
			fi.setRedirect(false);
			break;
		case 1:
			HttpSession session = request.getSession();
		session.setAttribute("id", id);
		System.out.println("id="+id);
			if(id.equals("admin")) {
				System.out.println("들어와!");
				session.setAttribute("product", makeproduct());
				fi.setPath("loginFrm.jsp");
				fi.setRedirect(false);
			}else {
				System.out.println("else 야");
				fi.setPath("loginFrm.jsp");
				fi.setRedirect(false);
			}
			
			
			break;

		default:
			break;
		}

		mDao.close();
		return fi;
	}

	private Object makeproduct() {
		StringBuilder sb=new StringBuilder();
		sb.append("<li><a href=\"ItemUpFrm\">");
		sb.append("<button style=\"background-color: #242424; border: none;\">상품등록</button>");
		sb.append("</a></li>");
		
		return sb.toString();
	}

	public ForwardInfo memberjoin() {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		Member mb = new Member();
		mb.setId(id);
		mb.setPw(pw);
		mb.setName(name);
		mb.setGender(gender);
		MemberDao mDao = new MemberDao();
		ForwardInfo fi = new ForwardInfo();
		boolean result = mDao.memberjoin(mb);
		if (result) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
		} else {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
		}
		return fi;
	}

	public ForwardInfo logout() {
		ForwardInfo fi=new ForwardInfo();
		HttpSession session=request.getSession();
		session.invalidate();
		fi.setPath("index.jsp");
		fi.setRedirect(true);
		
		return fi;
	}


}
