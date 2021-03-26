package common.listner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListner
 *
 */
@WebListener
public class SessionCounterListner implements HttpSessionListener {
	
	private static int activeSessions;

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         activeSessions++;
         System.out.println("세션 생성! : 현재 세션수는 [" + activeSessions + "]개 입니다.");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(activeSessions > 0) activeSessions--;
        System.out.println("세션 헤제! : 현재 세션수는 [" + activeSessions + "]개 입니다.");
    }
	
}
