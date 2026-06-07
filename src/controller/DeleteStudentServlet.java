package com.royal.controller;

import java.io.IOException;

import com.royal.bean.StudentBean;
import com.royal.bean.UserBean;
import com.royal.dao.StudentDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteStudentServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		
		UserBean userBean = 	(UserBean)session.getAttribute("userBean");
		
		if(userBean != null) 
		{
			System.out.println("DeleteStudentServlet : service()");
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			System.out.println("DeleteStudentServlet service() : id : " + id); 
			

			StudentDao dao = new StudentDao();
			
			int rowsAffected = dao.deleteStudentbyId(id);
			
			RequestDispatcher rd = null;
			
			if (rowsAffected > 0 ) 
			{
				rd = request.getRequestDispatcher("ListStudentServlet");// [ListStudentServlet--->studlist.jsp]
			} else 
			{
				request.setAttribute("dbError", "<font color='red'>Database Connection Error.</font>");
				rd = request.getRequestDispatcher("studlist.jsp");
			}
			rd.forward(request, response);
		}else 
		{
			request.setAttribute("loginAccess", "<font color='red'>Inavlid Access,\n\tPlease first login. </font>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
