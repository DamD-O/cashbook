package listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.StatsDao;

@WebListener
public class DriveListener implements ServletContextListener {
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
		//현재 접속자 카운트 속성
        sce.getServletContext().setAttribute("currentCount", 0); 
		System.out.println("db드라이버 로딩");
        //톰캣이 로딩하는 동안에 드라이버 로딩이 실행된다.
        try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
