package guru.springframework.sfpetclinic.controllers;

import guru.springframework.sfpetclinic.model.Owner;
import guru.springframework.sfpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/"})
//    public String ownersList(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//
//        return "owners/list";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", result);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") String ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(Long.valueOf(ownerId)));

        return mav;
    }
}
