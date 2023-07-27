package guru.springframework.sfpetclinic.controllers;

import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping("/vets")
    public String vetsList(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/list";
    }
}
