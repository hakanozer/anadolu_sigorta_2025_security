package com.works.controllers;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerLoginDto;
import com.works.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {

    private final CustomerService customerService;

    @GetMapping("")
    public String login(){
        return "login";
    }

    @PostMapping("")
    public String loginPost(
            @Valid CustomerLoginDto customerLoginDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam String data
    ){
        System.out.println(customerLoginDto);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "login";
        }
        Customer customer = customerService.login(customerLoginDto);
        System.out.println("loginStatus: " + customer);
        model.addAttribute("data", data);
        return "login";
    }

}
