package com.h2kinfosys.teachers.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeachersServlet extends HttpServlet {
	

	
	//Step1
		@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			System.out.println("Teachers App : Initialization");
		}
		//Step 2
		@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				// TODO Auto-generated method stub
			String teacherID = req.getParameter("teacherID");
			PrintWriter out = resp.getWriter();
			out.println("<html><body>");
			
			Enumeration<String> params= req.getParameterNames();
			String eachParams = null;
			out.println("<p>");
			while(params.hasMoreElements()) {
				eachParams = params.nextElement();
				out.println("Parameter Name : " +eachParams + " " + "Parameter Value : " +req.getParameter(eachParams));
			
			out.println("</p>");
			}
			out.println("<br>");
			
			Enumeration<String> headers = req.getHeaderNames();
			String eachHeader=null;
			out.println("<p>");
			while(headers.hasMoreElements()) {
				eachHeader = headers.nextElement();
				out.println("Header Name : " +eachHeader + " " + "Header Value : " +req.getHeader(eachHeader));
                 out.println("</p>");
				
				
			}
			out.println("<br>");
			out.println("<p>");
			Cookie[] cookie = req.getCookies();
			//String eachCookie=null;
			for (Cookie eachCookie:cookie) {
				out.println("Cookie Name : " +eachCookie.getName() +" " + "Cookie Value : " +eachCookie.getValue());
				
			}
			
			
			
			
			
			
			out.println("<p>");
			out.println("Teachers ID : " +teacherID);
			out.println("</p>");
			out.println("<p>");
			out.println("Checking Attribute information");
			out.println("</p>");
			out.println("<p>" +req.getAttribute("RequestAttrName")+ "</p>");
			HttpSession session = req.getSession(false);
			if (session!=null) {
			out.println("<p>" +session.getAttribute("SetAttributeName")+ "</p>");
			}
			out.println("<p>" +getServletContext().getAttribute("ContextAttributeName")+"</p>");
			Cookie cookie1 = new Cookie("TeacherApp","H2Kinfosys");
			resp.addCookie(cookie1);
			Cookie cookie2 = new Cookie("Target","UnitedStates");
			resp.addCookie(cookie2);
				
			}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			TeacherDAO teacherDAO = new TeacherDAO();
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String skill = req.getParameter("skill");
			TeacherDTO teacher = new TeacherDTO();
			teacher.setFirstName(firstName);
			teacher.setLastName(lastName);
			teacher.setSkill(skill);
			int teacherID=0;
			boolean flag=false;
			try {
				
				teacher.validTeacher(firstName,lastName);			
				          
						teacherID = teacherDAO.saveTeacher(teacher);
						PrintWriter out = resp.getWriter();
						out.println("<html><body>");
						if (teacher!=null) {
						out.println("<p>");
						out.println("Teacher ID : " +teacherID);
						out.println("</p>");
						out.println("<p>");
						out.println("Teacher First Name : " +teacher.getFirstName());
						out.println("</p>");
						out.println("<p>");
						out.println("Teacher Last Name : " +teacher.getLastName());
						out.println("</p>");
						out.println("<p>");
						out.println("Teacher Skill : " +teacher.getSkill());
						out.println("</p>");
						
						}
						out.println("</body></html>");
					
					
			
			}
			catch(InvalidTeacherException e) {
				e = new InvalidTeacherException("Teacher Invalid::");
				e.printStackTrace();
			}
			//int teacherID = teacherDAO.saveTeacher(teacher);
			
			
		}
	//Step 3
		@Override
			public void destroy() {
				// TODO Auto-generated method stub
				System.out.println("Teachers App : Destroyed");
			}

		
		
	}



