package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import dao.MemberDao;
import vo.Member;

@WebServlet("/MemberRegServlet")
public class MemberRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberDao memberDao;
	
	public MemberRegServlet() {
		memberDao = ServletContextConfig.getInstance().getMemberDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = Member.builder()
						.name(request.getParameter("name"))
						.id(request.getParameter("id"))
						.pw(request.getParameter("pw"))
						.tel(request.getParameter("tel"))
						.addr(request.getParameter("addr"))
						.build();	
		
		
		int result = memberDao.regMember(member);
		
		if(result == 1){
			response.sendRedirect("success.jsp");
		}else{
			response.sendRedirect("fail.jsp");
		}
	}

}
