package com.lezenford.spring.example.security.controller;

import com.lezenford.spring.example.security.config.SecurityConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("example")
public class ExampleController {
    private final SecurityConfiguration securityConfiguration;

    public ExampleController(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public String example() {
        System.out.println("test");
        return "test";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RolesAllowed("ADMIN")
    @GetMapping("test")
    public String test() {
        return "second page";
    }
}
