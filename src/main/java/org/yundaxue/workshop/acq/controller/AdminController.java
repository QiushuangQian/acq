package org.yundaxue.workshop.acq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yundaxue.workshop.acq.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 耿志强 on 2019/3/17.
 */

@Controller
public class AdminController {

    @GetMapping("/admin/login")
    public String loginGet(Model model) {
        return "login";
    }

    @PostMapping("/admin/login")
    public String loginPost(HttpServletRequest request, User user, Model model, HttpSession httpSession, final RedirectAttributes redirectAttrs) {
        return null;
    }
}
