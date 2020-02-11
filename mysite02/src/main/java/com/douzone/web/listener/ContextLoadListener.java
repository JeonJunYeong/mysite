package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 *
 */
@WebListener
public class ContextLoadListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext context =  servletContextEvent.getServletContext();
    	String contextConfigLocation = context.getInitParameter("contextconfigLocation");
    	System.out.println("Application Starts..."+contextConfigLocation);
    }
	
	
	
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
      
    }

}
