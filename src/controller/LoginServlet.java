package com.royal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.royal.bean.UserBean;
import com.royal.dao.UserDao;
import com.royal.util.StringUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out=	response.getWriter();
		RequestDispatcher rd = null;
		
		if(StringUtils.isValidString(username) && StringUtils.isValidString(password)) 
		{
			UserDao userDao = new UserDao();
			
			UserBean userBean = userDao.authenticateLogin(username, password);
			
			if(userBean != null) 
			{
				// session create 
				HttpSession session = request.getSession();
				
				session.setAttribute("userBean", userBean);
				
				rd = request.getRequestDispatcher("studentregi.jsp");
			}else 
			{
				request.setAttribute("loginError", "<font color='red'> Please Enter Valid Username and Password.</font");
				rd = request.getRequestDispatcher("login.jsp");
			}
		}else 
		{
			request.setAttribute("loginError", "<font color='red'> Please Enter Valid Username and Password.</font");
			rd = request.getRequestDispatcher("login.jsp");
		}
		
		rd.forward(request, response);
	}
}
