package com.juego.qqi.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {
    @RequestMapping(value = "/index", method = GET, headers = "Connection!=Upgrade")
    public String status() {
        return "ok";
    }
}
