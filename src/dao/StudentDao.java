package com.royal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

import com.royal.bean.StudentBean;
import com.royal.util.DBConnection;

public class StudentDao 
{

    public boolean isEmailUnique(String email)
    {
        boolean flag = true;

        String query = "SELECT email FROM student WHERE email=?";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                flag = false;  // email already exists
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return flag;
    }
    public boolean insertStudent(StudentBean bean)
    {
        boolean flag = false;

        String query = "INSERT INTO student(fullname,age,course,gender,hobbies,date_of_birth,email,mobile,address) "
                     + "VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setString(1, bean.getFullname());
            pstmt.setInt(2, bean.getAge());
            pstmt.setString(3, bean.getCourse());
            pstmt.setString(4, bean.getGender());

            String hobbies = String.join(",", bean.getHobby());
            pstmt.setString(5, hobbies);

            // DATE conversion
            pstmt.setDate(6, Date.valueOf(bean.getDob()));

            pstmt.setString(7, bean.getEmail());
            pstmt.setString(8, bean.getMobile());
            pstmt.setString(9, bean.getAddress());

            int rows = pstmt.executeUpdate();

            if(rows > 0)
                flag = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return flag;
    }

    public ArrayList<StudentBean> getStudentsByPage(int start, int total)
    {
        ArrayList<StudentBean> list = new ArrayList<>();

        String query = "SELECT * FROM student LIMIT ?,?";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setInt(1, start);
            pstmt.setInt(2, total);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                StudentBean bean = new StudentBean();

                bean.setId(rs.getInt("id"));
                bean.setFullname(rs.getString("fullname"));
                bean.setAge(rs.getInt("age"));
                bean.setCourse(rs.getString("course"));
                bean.setGender(rs.getString("gender"));

                String hobbiesStr = rs.getString("hobbies");
                if(hobbiesStr != null)
                    bean.setHobby(hobbiesStr.split(","));

                bean.setDob(rs.getDate("date_of_birth").toString());
                bean.setEmail(rs.getString("email"));
                bean.setMobile(rs.getString("mobile"));
                bean.setAddress(rs.getString("address"));

                list.add(bean);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public int getTotalStudentCount()
    {
        int total = 0;

        String query = "SELECT COUNT(*) FROM student";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                total = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return total;
    }


    public StudentBean getStudentById(int id)
    {
        StudentBean bean = null;

        String query = "SELECT * FROM student WHERE id=?";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                bean = new StudentBean();

                bean.setId(rs.getInt("id"));
                bean.setFullname(rs.getString("fullname"));
                bean.setAge(rs.getInt("age"));
                bean.setCourse(rs.getString("course"));
                bean.setGender(rs.getString("gender"));

                String hobbiesStr = rs.getString("hobbies");
                if(hobbiesStr != null)
                    bean.setHobby(hobbiesStr.split(","));

                bean.setDob(rs.getDate("date_of_birth").toString());
                bean.setEmail(rs.getString("email"));
                bean.setMobile(rs.getString("mobile"));
                bean.setAddress(rs.getString("address"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return bean;
    }

    public boolean updateStudent(StudentBean bean)
    {
        boolean flag = false;

        String query = "UPDATE student SET fullname=?,age=?,course=?,gender=?,hobbies=?,date_of_birth=?,email=?,mobile=?,address=? WHERE id=?";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setString(1, bean.getFullname());
            pstmt.setInt(2, bean.getAge());
            pstmt.setString(3, bean.getCourse());
            pstmt.setString(4, bean.getGender());

            String hobbies = String.join(",", bean.getHobby());
            pstmt.setString(5, hobbies);

            pstmt.setDate(6, Date.valueOf(bean.getDob()));

            pstmt.setString(7, bean.getEmail());
            pstmt.setString(8, bean.getMobile());
            pstmt.setString(9, bean.getAddress());
            pstmt.setInt(10, bean.getId());

            int rows = pstmt.executeUpdate();

            if(rows > 0)
                flag = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return flag;
    }

    public boolean deleteStudent(int id)
    {
        boolean flag = false;

        String query = "DELETE FROM student WHERE id=?";

        try(Connection conn = DBConnection.getDBInstance();
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if(rows > 0)
                flag = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return flag;
    }

}
