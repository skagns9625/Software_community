/**
 * ?¬ì?©ì?? ????ê°????? ???¥í???? Action
 * 
 */
package com.login.action;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.controller.CommandAction;
 
public class JoinAction implements CommandAction {
 
    public String requestPro(HttpServletRequest request,
 
    HttpServletResponse response) throws Throwable {
    	
    	request.setCharacterEncoding("euc-kr");
    	
    	//loginform?¼ë? ë¶??? ê°??? ???¥ë??? ë¶?ë¶?    	
		String id = request.getParameter("id");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	String Snum = request.getParameter("Snum");
    	
    	//mysql ?°ì?´í?°ë??´ì?¤ì?? ???????? ?´ë???? ???¼ì?´ë? ????
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	//?°ì?´í?? ë²??´ì?? ?°ê²° ê´??? ë³??? ????
    	Connection conn = null;
    	PreparedStatement pstmt = null;    	
    	
    	try{ 		
        	
    		String jdbcDriver = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC"; 

			String dbUser = "root";
			String dbPass = "root";
    		
			//?°ì?´í?°ë??´ì?¤ì?? ?°ê²°
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
    		
			//ê°? ??? ?´ë?¹í???? ë³????¤ì?? ?£ì?´ì???.
      		pstmt = conn.prepareStatement(
    				"insert into usr values(?,?,?,?,now())");
    				pstmt.setString(1, id);
    				pstmt.setString(2, password);
    				pstmt.setString(3, name);
    				pstmt.setString(4, Snum);
    				
    		// pstmt?? ???¥ë?? ì¿¼ë¦¬ ?¤í??
    				pstmt.executeUpdate();
    		
    		//ë¡?ê·¸ì?? ê´??? ?¸ì?? ????
    		HttpSession session = request.getSession();
    		
    		session.setAttribute("id", id);	
    				
    	} catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			/*?°ì?´í?? ë²??´ì?¤ì?? ??????ê³? ???? ?°ì?´í?? ë²??´ì?? ?°ê²°?? ???´ì???
			  ??ê·¸ë?? ê²½ì?? ?????? ??ë¹?ê°? ë°???
			*/
    		if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
    		if(conn != null) try{conn.close();} catch(SQLException ex){}
    		}
    	
 
        return "join.jsp";
 
    }
 
}