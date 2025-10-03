package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JwtUiController {

    @GetMapping("jwt/login")
    public String loginView() {
        return "jwt_login";
    }

    @GetMapping("user/profile")
    public String profileView() {
        return "profile";
    }
}


