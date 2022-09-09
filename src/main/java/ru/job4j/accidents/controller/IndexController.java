package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Danil Nikishin");
        model.addAttribute("accidents", service.getAllAccident());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", getTypes());
        model.addAttribute("user", "Danil Nikishin");
        return "createAccident";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.addAccident(accident);
        return "redirect:/index";
    }

    @GetMapping("/replace/{id}")
    public String replace(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", "Danil Nikishin");
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("types", getTypes());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, @RequestParam("id") int id) {
        service.updateAccident(id, accident);
        return "redirect:/index";
    }

    private List<AccidentType> getTypes() {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        return types;
    }

}
