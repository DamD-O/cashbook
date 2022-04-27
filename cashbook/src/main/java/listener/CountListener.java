package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.StatsDao;
import vo.Stats;

@WebListener
public class CountListener implements HttpSessionListener {
	private StatsDao statsDao;
    public void sessionCreated(HttpSessionEvent se)  { 
    	//새로운 세션이 발생하면 생성되는 메서드
    	//context에서 cuurentCount 값을가져와서
    	int currentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount+1);//cuurentCount+1한다
    
    	//날짜별 ->DB이용
    	this.statsDao = new StatsDao();
    	Stats stats = statsDao.selectStatsOneByNow();
    	if(stats == null) { //오늘 날짜의 카운트가 없다.
    		statsDao.insertStats(); //오늘날짜로 count+1
    	}else { //오늘 날짜 카운트 있다
    		statsDao.updateStatsByNow(); //오늘 날짜 count +1;
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
        //기존의 세션이 사라졌을때 생성되는 메서드
    	int cuurentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", cuurentCount-1);
    
    }
	
}
