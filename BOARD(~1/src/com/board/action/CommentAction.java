/**
 * db와 연결하기위해 커넥션 객체 생성하는데, 미리 connection 객체만들고 가져다 쓰는것. 다쓰고 반환
 */
package com.board.action;
 
import java.sql.*;
import java.util.ArrayList;
 


import javax.servlet.http.HttpServletRequest;
 
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;

import com.board.beans.comment;
import com.board.controller.CommandAction;
 
public class CommentAction implements CommandAction {
	
    public String requestPro(HttpServletRequest request,
 
    HttpServletResponse response) throws Throwable {
 
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	int num = Integer.parseInt(request.getParameter("num"));
    	Connection conn = null;
    	Statement stmt = null;    	
    	ResultSet rs = null;
    	
    	try {
    		
    		HttpSession session = request.getSession();
    		String id = (String) session.getAttribute("id");
    		if(id == null){    			
    			return "loginerror.jsp";
    		}
    		
    		String jdbcDriver = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC";
    		
    			//	+
    			//				"useUnicode=true&characterEncoding = euc-kr";
    		String dbUser = "root";
    		String dbPass = "root";
    		
    		String query = "select * from comment1 where num = "+num;
    		
    		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
    		
    		stmt = conn.createStatement();    		
    		rs = stmt.executeQuery(query);    		
    		
    	
    		ArrayList<comment> commentList = new ArrayList<comment>();
    		
    		while(rs.next()){
    			comment comments = new comment();
    			comments.setNum(rs.getInt("num"));    		
    			comments.setId(rs.getString("id"));
    			comments.setContent(rs.getString("content"));
    			comments.setBoarddate(rs.getString("boarddate"));
    			commentList.add(comments);
    		}
    		request.setAttribute("commentList",commentList);
    		 
    		
    	} catch(SQLException ex){
    		
    	} finally{
    		if(rs != null) try{rs.close();} catch(SQLException ex){}
    		if(stmt != null) try{stmt.close();} catch(SQLException ex) {}
    		
    		if(conn != null) try{conn.close();} catch(SQLException ex) {}
    	}
 
        return "content.jsp";
 
    }
 
}