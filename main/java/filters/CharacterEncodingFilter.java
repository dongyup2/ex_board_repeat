package filters;

import java.io.IOException; 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CharacterEncodingFilter extends HttpFilter implements Filter {
       
	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//전처리	
		HttpServletRequest httpRequest = (HttpServletRequest) request;//request 객체를 사용하기위해 다운캐스팅
		//일반적으로 GET 방식에서는 요청 데이터를 URL에 담아서 보내므로 인코딩이 필요 없지만,
		//POST 방식에서는 HTTP 요청 데이터가 본문에 담아져서 전달되기 때문에 인코딩
		
		if(!httpRequest.getMethod().equalsIgnoreCase("get")) {// getMethod() 클라이언트가 보낸 요청(Request)이 
			request.setCharacterEncoding("UTF-8");//무슨 HTTP 메서드를 사용했는지 결정하는 메소드.
			System.out.println("인코딩됨!");
		}
		chain.doFilter(request, response); //서블릿
		//후처리
	}

	public void init(FilterConfig fConfig) throws ServletException {
//		ServletContextConfig.getInstance();
	}

}
