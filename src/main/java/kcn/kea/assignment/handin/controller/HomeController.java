package kcn.kea.assignment.handin.controller;

import kcn.kea.assignment.handin.persistence.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* Home controller manages page requests that do not involve body-interactions */
@Controller
public class HomeController
{
    @Autowired
    private BodyRepository bodyRepository;

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("bodyCount",bodyRepository.count());
        model.addAttribute("bodies",bodyRepository.findAll());

        return "home";
    }
}
