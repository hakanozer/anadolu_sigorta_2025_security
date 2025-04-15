package com.works.controllers;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

}
