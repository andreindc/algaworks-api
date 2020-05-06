package com.algaworksapi.algaworksapi.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
//Remueve el token
    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); //true en producción
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0); //Cuando quiero que expire
        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());

    }

}
