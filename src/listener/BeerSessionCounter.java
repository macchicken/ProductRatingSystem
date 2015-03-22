package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class BeerSessionCounter implements HttpSessionListener {

	private static int activeSessions;

	public static int getActiveSessions() {
		return activeSessions;
	}


	@Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		System.out.println("session created,current active sessions: "+activeSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		activeSessions--;
		System.out.println("session destroyed,current active sessions: "+activeSessions);
	}

}
