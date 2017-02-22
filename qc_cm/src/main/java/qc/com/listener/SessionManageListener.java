package qc.com.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Package:com.huipu.common.listener
 * @ClassName: SessionManageListener
 * @Description: 公用session监听器
 * @Version:v1.0
 */
@SuppressWarnings("unchecked")
public class SessionManageListener implements HttpSessionListener {
    private SessionContext sc = SessionContext.getInstance();
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		// 在application范围由一个HashSet集保存所有的session
		Map<String,HttpSession> sessionsMap = (Map<String, HttpSession>)application.getAttribute("sessionsMap");
        if (sessionsMap == null) {
        	sessionsMap = new HashMap<String,HttpSession>();
        	application.setAttribute("sessionsMap", sessionsMap);
        }
    	sc.AddSession(event.getSession());
        sessionsMap.put(session.getId(),session);
	}
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		Map<String,HttpSession> sessionsMap = (Map<String, HttpSession>) application.getAttribute("sessionsMap");
        if(session!=null){
        	if(null!=sessionsMap.get(session.getId())){
        		sessionsMap.remove(session.getId());
            	sc.DelSession(event.getSession());
        	}
        }
        if (sessionsMap.isEmpty()) {
        	application.removeAttribute("sessionsMap");
        }
	}
}
