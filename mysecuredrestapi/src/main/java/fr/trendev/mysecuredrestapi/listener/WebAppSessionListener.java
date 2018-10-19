package fr.trendev.mysecuredrestapi.listener;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Tracks HTTP Sessions lifecycle changes
 *
 * @author jsie
 */
@WebListener
public class WebAppSessionListener implements HttpSessionListener,
        HttpSessionIdListener {

    private static final Logger LOG = Logger.getLogger(
            WebAppSessionListener.class.getName());

    private static final DateFormat df = DateFormat.getDateTimeInstance(
            DateFormat.FULL, DateFormat.FULL);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession hs = se.getSession();
        LOG.log(Level.INFO, "Session [{0}] has been created on {1}",
                new Object[]{
                    hs.getId(),
                    df.format(new Date(hs.getCreationTime()))
                });
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession hs = se.getSession();
        LOG.log(Level.INFO, "Session [{0}] has been destroyed on {1}",
                new Object[]{
                    hs.getId(),
                    df.format(new Date())
                });
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
        HttpSession hs = se.getSession();
        LOG.log(Level.INFO,
                "Session [{0}] has been changed to [{1}] on {2}",
                new Object[]{
                    oldSessionId,
                    hs.getId(),
                    df.format(new Date())
                });
    }

}
