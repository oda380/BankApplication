package com.finalproject.BankApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin/adminHome/";
        } else if (request.isUserInRole("TELLER")) {
            return "redirect:/tellerdashboard/";
        } else
            return "redirect:/user/userHome";
    }
}