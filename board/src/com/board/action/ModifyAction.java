/**
 * �Խ��� ���� �����ϴ� Action
 */
package com.board.action;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.controller.CommandAction;
 
public class ModifyAction implements CommandAction {
 
    public String requestPro(HttpServletRequest request,
 
    HttpServletResponse response) throws Throwable {
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	String url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC";
    	String dbUser = "root";
    	String dbPass = "root";
    	Connection conn = null;
    	Statement stmt = null;    	    	
    		try{
    			
    			HttpSession session = request.getSession();
            	
        		if(session.getAttribute("id") == null){
        			return "loginerror.jsp";
        		}
    			
    			request.setCharacterEncoding("euc-kr");
    			String num = request.getParameter("num");    			
    			String subject = request.getParameter("subject");
    			String content = request.getParameter("content");
    			String category = request.getParameter("category");
    			
    			conn = DriverManager.getConnection(url,dbUser,dbPass);    			    			    			
    					
    			stmt = conn.createStatement();
   			    			    		
    			String sql = "UPDATE board1 SET subject='" + subject + "' ,content='"+ content +  "' ,category='"+ category +   						
    						"' WHERE num=" + num;				
    			stmt.executeUpdate(sql);    			

    			stmt.close();
    			conn.close();    			
    		 
     } catch(SQLException e) {
    	System.out.println( e.toString() );
    } finally{    	
    	if(stmt != null) try{stmt.close();} catch(SQLException ex){}			
		if(conn != null) try{conn.close();} catch(SQLException ex){}
		}
    	
    	
        return "content.do";
 
    }
 
}