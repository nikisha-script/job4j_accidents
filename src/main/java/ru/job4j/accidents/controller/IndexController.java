package ru.job4j.accidents.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;

@Controller
public class IndexController {

    private final AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        model.addAttribute("accidents", service.getAllAccident());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRyles());
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        return "createAccident";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("typeid") String idType,
                       @RequestParam("ruleid") List<String> idRule) {
        accident.setType(service.findTypeById(Integer.parseInt(idType)));
        idRule.forEach(s -> accident.getRules().add(service.findRuleById(Integer.parseInt(s))));
        service.addAccident(accident);
        return "redirect:/index";
    }


    @GetMapping("/replace/{id}")
    public String replace(Model model, @PathVariable("id") int id) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRyles());
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident,
                                 @RequestParam("typee") String idType,
                                 @RequestParam("rulee") List<String> idRule) {
        accident.setType(service.findTypeById(Integer.parseInt(idType)));
        idRule.forEach(s -> accident.getRules().add(service.findRuleById(Integer.parseInt(s))));
        service.updateAccident(accident);
        return "redirect:/index";
    }


}
