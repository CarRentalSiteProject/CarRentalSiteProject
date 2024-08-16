package com.tcg.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import vo.MemberVo;

@Controller
@SessionAttributes("id")
public class LoginAction {
    
    @Autowired
    MemberDao dao;
    
    @GetMapping("/login")
    public String loginForm2(Model m) {
        m.addAttribute("mes", "登入");
        return "redirect:/PrimeDrive_BS5/login.jsp";
    }
    
    @PostMapping("/login")
    public String checkIDPaswd(MemberVo vo, Model m) {
        System.out.println("checkIDPaswd2...");
        System.out.println(vo.getUsername());
        System.out.println(vo.getPassword());
        
        String id = vo.getUsername();
        String paswd = vo.getPassword();
        String tmpPaswd = dao.get(id);
        
        if (tmpPaswd != null && tmpPaswd.equals(paswd)) {
            m.addAttribute("id", id);
            return "redirect:/PrimeDrive_BS5/index.jsp";
        } else {
            m.addAttribute("mes", "帳號密碼錯誤");
            return "redirect:/PrimeDrive_BS5/login.jsp"; // 返回 login 視圖
        }
    }
}
