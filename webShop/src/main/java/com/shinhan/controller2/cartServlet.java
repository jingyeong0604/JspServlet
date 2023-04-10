package com.shinhan.controller2;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cartServlet
 */
//webshop까지가 현재 경로임.
@WebServlet("/cart/addCart.do")
public class cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		RequestDispatcher rd;
		rd=request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String product = request.getParameter("product");
		int count = Integer.parseInt(request.getParameter("count"));
		
		HttpSession session = request.getSession();//있으면 얻고 없으면 새로 만든다.
		//cart가 있는지보고
		Object obj = session.getAttribute("cart");
		HashMap<String, Integer> cart = null;
		if(obj==null) {
			cart =new HashMap<>();
			cart.put(product, count);
			session.setAttribute("cart", cart);
			
		}else {
			//카트를 얻어와서
			cart = (HashMap<String, Integer>)obj;
			
			if(cart.containsKey(product)) {
				int originalCount = cart.get(product);
				
				//원래갯수랑 현재갯수 더해주기
				cart.put(product, originalCount + count);
			}else {
				cart.put(product, count);
			}

			
		}
		//post에서 get으로 가는것!
		response.sendRedirect("addCart.do");
		
		
		//cart.put(product, count);
		
	}


}
