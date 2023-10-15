package config;

import dao.MemberDao;
import dao.MemberDaoImpl;
import lombok.Getter;

@Getter
public class ServletContextConfig {
	private static ServletContextConfig instance = null;
	
	private MemberDao memberDao;
	
	private ServletContextConfig() {}
	
	public static ServletContextConfig getInstance() {
		if(instance == null) {
			instance = new ServletContextConfig();
			instance.setIoC();
		}
		return instance;
	}
	private void setIoC() {
		if(memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
	}
}
