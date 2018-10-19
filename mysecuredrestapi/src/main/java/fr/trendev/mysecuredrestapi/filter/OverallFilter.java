package fr.trendev.mysecuredrestapi.filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jsie
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class OverallFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(OverallFilter.class.
            getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.log(Level.INFO, "OverallFilter: init in progress...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;

        Principal user = req.getUserPrincipal();

        HttpSession session = req.getSession(false);

        LOG.log(Level.INFO, "{3} / [{1}] has requested {2} {0}",
                new Object[]{req.getRequestURL(), (user != null) ? user.
                    getName() : "an ANONYMOUS user", req.getMethod(), req.
                    getRemoteAddr()});

        try {
            /**
             * Adds a timestamp in the current session in order to store the
             * last access time and not the last access time of the previous
             * request...
             *
             */
            if (user != null && session != null) {
                long time = System.currentTimeMillis();
                LOG.log(Level.INFO, "Updating the timestamp on {0}", new Date(
                        time));
                session.
                        setAttribute("RQT_TIMESTAMP", time);
            }
            chain.doFilter(request, response);
        } catch (IOException | ServletException ex) {
            LOG.log(Level.WARNING,
                    "The request {0} is aborted : Session already invalidated ! ",
                    req.getRequestURL());
            rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    @Override
    public void destroy() {
        LOG.log(Level.INFO, "OverallFilter: destroying...");
    }

}
