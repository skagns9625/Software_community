/**
 * db¿Í ¿¬°áÇÏ±âÀ§ÇØ Ä¿³Ø¼Ç °´Ã¼ »ý¼ºÇÏ´Âµ¥, ¹Ì¸® connection °´Ã¼¸¸µé°í °¡Á®´Ù ¾²´Â°Í. ´Ù¾²°í ¹ÝÈ¯
 */
package com.board.action;
 
import java.sql.*;
import java.util.ArrayList;
 


import javax.servlet.http.HttpServletRequest;
 
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;

import com.board.beans.comment;
import com.board.controller.CommandAction;
 
public class CommentWriteAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request,
			 
		    HttpServletResponse response) throws Throwable {
		    	
		    	request.setCharacterEncoding("euc-kr");
		    	//??ëª©ê³¼ ?´ì?©ì?? ???? ë°??? ë³????? ????
		    	int num;
		    	String comment = request.getParameter("comment");
		    	
		    	String id=null;
		    	String query=null;
		    	Class.forName("com.mysql.cj.jdbc.Driver");
		    	
		    	Connection conn = null;
		    	PreparedStatement pstmt = null;
		    	ResultSet rs=null;
		    	num=Integer.parseInt(request.getParameter("num"));
		    	try{
		    		HttpSession session = request.getSession();
		    		//?¸ì???? ?½ì?? ë¡?ê·¸ì?? ????ê°? ????ë©? ë¡?ê·¸ì?? ì°½ì?¼ë? ?´ë??
		        	id = (String) session.getAttribute("id");
		    		if( id == null){
		    			return "loginerror.jsp";
		    		}
		    		
		    		String jdbcDriver = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC";
		    		          // +
								//		"useUnicode=true&characterEncoding = euc-kr";
					String dbUser = "root";
					String dbPass = "root";
		    		
					conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
					
		      		pstmt = conn.prepareStatement(      				
		      				"insert into comment1 values("+num+",?,?,now())");
		    				pstmt.setString(1, id);
		    				pstmt.setString(2, comment);
		    				//ì¿¼ë¦¬ ?¤í??
		    				pstmt.executeUpdate();
		    			
		    				
		    				
		    	} catch(SQLException ex){
					ex.printStackTrace();
				}finally{
					
		    		if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
		    		if(conn != null) try{conn.close();} catch(SQLException ex){}
		    		}


		     //   return "write.jsp";
		        return  "category.do";
		    }
 
}