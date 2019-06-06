/**
 */
package com.login.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.controller.CommandAction;

public class LoginAction implements CommandAction{

	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");
		
		String id = request.getParameter("id");
    	String password = request.getParameter("password");    	
    	    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	HttpSession session = request.getSession();
    	Connection conn = null;    	
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	try{
    		
    		String jdbcDriver = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC";
    		         //+
					 //			"useUnicode=true&characterEncoding = euc-kr";
			String dbUser = "root";
			String dbPass = "root";
    		
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);			
			stmt = conn.createStatement();
		
			String query = "select * from usr where id = '"+id+"' and password = '"+password+"';";					
				
			rs = stmt.executeQuery(query);
			
		
			Boolean isLogin = false;
			
			while(rs.next()){
				isLogin = true;
			}			
		
			if(isLogin){				
				session.setAttribute("id", id);
			
			}else if(!isLogin && id != null){
				return "loginerror2.jsp";
			}
    	} catch(SQLException ex){

    	}finally{
    
    		if(stmt != null) try{stmt.close();} catch(SQLException ex){}
    		if(conn != null) try{conn.close();} catch(SQLException ex){}
    		if(rs != null) try{rs.close();} catch(SQLException ex){}
    		}    	       	
    	return "main.jsp";
	}
	
}
