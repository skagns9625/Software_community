/**
 * 글을 작성 하고 데이터베이스에 넣는 Action
 */
package com.board.action;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.beans.board;
import com.board.controller.CommandAction;
import com.mysql.cj.protocol.Resultset;
 
public class BestPageAction implements CommandAction {
 
	  public String requestPro(HttpServletRequest request,
			  
			    HttpServletResponse response) throws Throwable {
			 
			    	Class.forName("com.mysql.cj.jdbc.Driver");    	    
			    	Connection conn = null;
			    	Statement stmt = null;
			    	ResultSet rs = null;    	
			    	
			    	
			    	String opt = request.getParameter("opt");
			    	String condition = request.getParameter("condition");
			    	
			    	try {
			    		HttpSession session = request.getSession();
			    		 		    		
			    		String id = (String) session.getAttribute("id");    
			 
			    		if(id == null){    			
			    			return "loginerror.jsp";
			    		}
			    		
			    		String jdbcDriver = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC";
			    		           // +
			    				
			    		String dbUser = "root";
			    		String dbPass = "root";
			    		String query = null; 
			    		
			    		query = "SELECT * FROM board1 WHERE score>=10;";			
			    		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			    		
			    		stmt = conn.createStatement();    		
			    		rs = stmt.executeQuery(query);  
    		
    		ArrayList<board> bestlist = new ArrayList<board>();    		
    		
    		while(rs.next()){
    			board best = new board();
    			best.setNum(rs.getInt("num"));
    			best.setSubject(rs.getString("subject"));
    			best.setContent(rs.getString("content"));
    			best.setId(rs.getString("id")); 
    			best.setBoarddate(rs.getString("boarddate"));
    			best.setScore(rs.getString("score"));
    			best.setCategory(rs.getInt("category"));
    			bestlist.add(best);
    			
    		}
    		request.setAttribute("bestList",bestlist);
    		
    		
    	} catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			
    		if(stmt != null) try{stmt.close();} catch(SQLException ex){}
    		if(conn != null) try{conn.close();} catch(SQLException ex){}
    		}
    


    	//return "SelectCategoryAction.java";
        return "main.jsp";
 
    }
 
}