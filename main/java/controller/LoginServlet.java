package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import dao.MemberDao;
import dao.MemberDaoImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberDao memberDao;
	
	public LoginServlet() {
		memberDao = ServletContextConfig.getInstance().getMemberDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("loginForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	
		int result = memberDao.loginCheck(id, pw);
		
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("id", id);
			response.sendRedirect("mainpage.jsp");
		}else {
			response.sendRedirect("loginForm.jsp");
		}
	}
}
