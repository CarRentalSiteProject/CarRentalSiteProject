package com.tcg.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
public class LoginAction {

    @Autowired
    MemberDao dao;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("mes", "登入");
        return "PrimeDrive_BS5/index";
    }

    @PostMapping("/login")
    public String checkIDPaswd(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "paswd", required = false) String password,
            Model model) {
        System.out.println("checkIDPaswd...");
        System.out.println("id: " + id);
        System.out.println("password: " + password);

        if (dao.validateLogin(id, password)) {
            model.addAttribute("id", id);
            return "redirect:/index.jsp";
        } else {
            model.addAttribute("mes", "帳號密碼錯誤");
            return "login";
        }
    }
}
