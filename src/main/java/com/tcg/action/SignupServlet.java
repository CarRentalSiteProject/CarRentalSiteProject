package com.tcg.action;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.MemberVo;
import com.tcg.action.MemberDao;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

            //int memberID = Integer.parseInt(memberIDParam);
    	try {
            String ageParam = request.getParameter("age");
            if (ageParam == null) {
                // 處理缺少參數的情況
                response.sendRedirect("Signup.jsp?error=missingParameters");
                return;
            }

            int age = Integer.parseInt(ageParam);
            String gender = request.getParameter("gender");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String licenseNub = request.getParameter("licenseNub");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            //boolean login = request.getParameter("login") != null;

            MemberVo member = new MemberVo();
            //member.setMemberID(memberID);
            member.setAge(age);
            member.setGender(gender);
            member.setName(name);
            member.setEmail(email);
            member.setLicenseNub(licenseNub);
            member.setAddress(address);
            member.setPhone(phone);
            member.setPassword(password);
            //member.setLogin(login);

            MemberDao dao = new MemberDao();
            dao.addMember(member);

            response.sendRedirect("Member.jsp");
    	 } catch (NumberFormatException e) {
    	        // 處理解析錯誤的情況
    	        response.sendRedirect("Signup.jsp?error=invalidInput");
    	    }
        
    }

}

