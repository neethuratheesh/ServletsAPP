package com.h2kinfosys.browserclock.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrowserClock extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Initializing Browser Clock");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setIntHeader("Refresh",1);
		resp.setContentType("text/html");
		
		Calendar cal = new GregorianCalendar();
		String am_pm = null;
		int hour = cal.get(cal.HOUR);
		int minutes = cal.get(cal.MINUTE);
		int seconds = cal.get(cal.SECOND);
		if (cal.get(cal.AM_PM)==0) {
			am_pm = "AM";
		}
		else
			am_pm = "PM";
		PrintWriter out = resp.getWriter();
		String CT = hour+":"+minutes+":"+seconds;
		out.println("<html><body>");
		out.println("<p>");
		out.println("Current Time is : " +CT);
		out.println("</p>");
		out.println("</body></html>");
		
		
		
	}
	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroying Browser clock");
	}

	
	

}
