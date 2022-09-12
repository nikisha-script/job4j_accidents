package ru.job4j.accidents.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.dataspring.AuthorityRepository;
import ru.job4j.accidents.repository.dataspring.UserRepository;

import java.util.Optional;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @GetMapping("/reg")
    public String regPage(Model model, @RequestParam(name = "error", required = false) String errorMessage) {
        String errorMsg = null;
        if (errorMessage != null) {
            errorMsg = "Пользователь с таким ником уже существует";
        }
        model.addAttribute("errorMessage", errorMsg);
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        Optional<User> userDb = users.findByUsername(user.getUsername());
        if (userDb.isPresent()) {
            return "redirect:/reg?error=true";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

}
