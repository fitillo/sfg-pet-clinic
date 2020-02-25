package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "index.html"})
    String index() {
        return "index";
    }

    @GetMapping({"/oups"})
    String findOwners() {
        return "notimplemented";
    }
}
