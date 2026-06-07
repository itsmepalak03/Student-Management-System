package com.royal.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.royal.bean.StudentBean;
import com.royal.bean.UserBean;
import com.royal.dao.StudentDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListStudentServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("ListStudentServlet : service()");

        /* =========================================================
           1) SESSION CHECK
        ========================================================= */

        HttpSession session = request.getSession(false);

        if (session == null)
        {
            request.setAttribute("loginAccess", "Please login first.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        UserBean userBean = (UserBean) session.getAttribute("userBean");

        if (userBean == null)
        {
            request.setAttribute("loginAccess", "Please login first.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        /* =========================================================
           2) PAGINATION LOGIC
        ========================================================= */

        StudentDao dao = new StudentDao();

        int page = 1;                // default page
        int recordsPerPage = 5;      // 👈 Change to 10 if needed

        // If user clicks page number
        if (request.getParameter("page") != null)
        {
            try
            {
                page = Integer.parseInt(request.getParameter("page"));
            }
            catch (Exception e)
            {
                page = 1;   // fallback safety
            }
        }

        // Calculate starting index
        int start = (page - 1) * recordsPerPage;

        System.out.println("Current Page : " + page);
        System.out.println("Start Index  : " + start);

        /* =========================================================
           3) FETCH RECORDS FROM DAO
        ========================================================= */

        ArrayList<StudentBean> list = dao.getStudentsByPage(start, recordsPerPage);

        /* =========================================================
           4) TOTAL RECORD COUNT (For total pages calculation)
        ========================================================= */

        int totalRecords = dao.getTotalStudentCount();

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        System.out.println("Total Records : " + totalRecords);
        System.out.println("Total Pages   : " + totalPages);

        /* =========================================================
           5) SEND DATA TO JSP
        ========================================================= */

        request.setAttribute("list", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher rd = request.getRequestDispatcher("studlist.jsp");
        rd.forward(request, response);
    }
}
