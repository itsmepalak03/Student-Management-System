package com.royal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.royal.bean.UserBean;
import com.royal.util.DBConnection;

public class UserDao 
{
	public UserBean authenticateLogin(String emailId,String password)
	{
		String isValidUserQuery = "SELECT * from users WHERE email=? AND password=?";
		
		System.out.println("isValidUserQuery : " + isValidUserQuery);
		
		Connection conn = DBConnection.getDBInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserBean userBean = null;
		if (conn!=null) 
		{
			try 
			{
				pstmt = conn.prepareStatement(isValidUserQuery);
				pstmt.setString(1, emailId);
				pstmt.setString(2, password);
				
				rs = pstmt.executeQuery();
			
				if(rs.next()) 
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String email = rs.getString(3);
					String password1= rs.getString(4);
					
					userBean = new UserBean(id, name, email, password1);
				}
				
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		} else 
		{
			System.out.println("UserDao : authenticateLogin() Db not connected");
		}
		return userBean;
	}
}
