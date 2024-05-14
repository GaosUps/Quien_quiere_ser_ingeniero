package io.github.gaosups.qqi.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/index", headers = "Connection!=Upgrade")
    public String status() {
        return "ok";
    }
}
