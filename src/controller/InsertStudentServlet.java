package com.royal.controller;

import java.io.IOException;

import com.royal.bean.StudentBean;
import com.royal.bean.UserBean;
import com.royal.dao.StudentDao;
import com.royal.util.StringUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InsertStudentServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("InsertStudentServlet : doPost()");

        HttpSession session = request.getSession(false);

        // ===== SESSION CHECK =====
        if(session == null || session.getAttribute("userBean") == null)
        {
            request.setAttribute("loginAccess", "<font color='red'>Please login first.</font>");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        boolean flag = false;

        StudentBean bean = new StudentBean();

        // =========================
        // 1) FULL NAME
        // =========================
        String fullname = request.getParameter("fullname");

        if(StringUtils.isValidString(fullname))
        {
            bean.setFullname(fullname);
        }
        else
        {
            flag = true;
            request.setAttribute("fullnameErr", "<font color='red'>Enter valid fullname</font>");
        }

        // =========================
        // 2) AGE
        // =========================
        String ageStr = request.getParameter("age");

        if(StringUtils.isValidString(ageStr))
        {
            bean.setAge(Integer.parseInt(ageStr));
        }
        else
        {
            flag = true;
            request.setAttribute("ageErr", "<font color='red'>Enter valid age</font>");
        }

        // =========================
        // 3) COURSE
        // =========================
        String course = request.getParameter("course");

        if(StringUtils.isValidString(course))
        {
            bean.setCourse(course);
        }
        else
        {
            flag = true;
            request.setAttribute("courseErr", "<font color='red'>Select course</font>");
        }

        // =========================
        // 4) GENDER
        // =========================
        String gender = request.getParameter("gender");

        if(StringUtils.isValidString(gender))
        {
            bean.setGender(gender);
        }
        else
        {
            flag = true;
            request.setAttribute("genderErr", "<font color='red'>Select gender</font>");
        }

        // =========================
        // 5) HOBBIES
        // =========================
        String hobbies[] = request.getParameterValues("hobby");

        if(hobbies != null && hobbies.length > 0)
        {
            bean.setHobby(hobbies);
        }
        else
        {
            flag = true;
            request.setAttribute("hobbyErr", "<font color='red'>Select at least one hobby</font>");
        }

        // =========================
        // 6) DOB
        // =========================
        String dob = request.getParameter("dob");

        if(StringUtils.isValidString(dob))
        {
            bean.setDob(dob);
        }
        else
        {
            flag = true;
            request.setAttribute("dobErr", "<font color='red'>Select DOB</font>");
        }

        // =========================
        // 7) EMAIL
        // =========================
        String email = request.getParameter("email");

        StudentDao dao = new StudentDao();

        if(StringUtils.isValidString(email))
        {
            if(dao.isEmailUnique(email))
            {
                bean.setEmail(email);
            }
            else
            {
                flag = true;
                request.setAttribute("emailErr", "<font color='red'>Email already exists</font>");
            }
        }
        else
        {
            flag = true;
            request.setAttribute("emailErr", "<font color='red'>Enter valid email</font>");
        }

        // =========================
        // 8) MOBILE
        // =========================
        String mobile = request.getParameter("mobile");

        if(StringUtils.isValidString(mobile))
        {
            bean.setMobile(mobile);
        }
        else
        {
            flag = true;
            request.setAttribute("mobileErr", "<font color='red'>Enter valid mobile</font>");
        }

        // =========================
        // 9) ADDRESS
        // =========================
        String address = request.getParameter("address");

        if(StringUtils.isValidString(address))
        {
            bean.setAddress(address);
        }
        else
        {
            flag = true;
            request.setAttribute("addressErr", "<font color='red'>Enter valid address</font>");
        }

        request.setAttribute("sbean", bean);

        RequestDispatcher rd;

        // =========================
        // IF VALIDATION FAIL
        // =========================
        if(flag)
        {
            rd = request.getRequestDispatcher("studentregi.jsp");
        }
        else
        {
            boolean status = dao.insertStudent(bean);

            if(status)
            {
                rd = request.getRequestDispatcher("ListStudentServlet");
            }
            else
            {
                request.setAttribute("dbError", "<font color='red'>Database Error</font>");
                rd = request.getRequestDispatcher("studentregi.jsp");
            }
        }

        rd.forward(request, response);
    }
}
