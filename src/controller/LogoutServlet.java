package com.royal.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {

        // get existing session (do not create new)
        HttpSession session = request.getSession(false);

        // if session exists -> invalidate
        if (session != null) {
            session.invalidate();
        }

        // set logout message
        request.setAttribute("logoutSuccess", "Logout Successfully.");

        // forward to login page
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
