package com.finalproject.BankApplication.controller;


import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value={ "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public ModelAndView signup(){
        System.out.println("Loading the Signup Page");
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Customer customer, BindingResult bindingResult) {
        System.out.println("I'm in the create new user method");
        ModelAndView modelAndView = new ModelAndView();
        Customer userExists = customerService.findUserByEmail(customer.getEmail());
        System.out.println("getting email " + userExists);
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "email.inuse",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            customerService.saveUser(customer);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("customer", new Customer());
            modelAndView.setViewName("signup");

        }
        return modelAndView;
    }

    @RequestMapping(value="/customer/customerDashboard", method = RequestMethod.GET)
    public ModelAndView customer(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome Customer: " + customer.getFirstName() + " " + customer.getLastName());
        modelAndView.addObject("userMessage","LKM bank!");
        modelAndView.setViewName("customer/customerDashboard");
        return modelAndView;
    }
}