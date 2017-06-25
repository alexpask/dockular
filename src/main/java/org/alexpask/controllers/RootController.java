package org.alexpask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by alexpask on 24/06/2017.
 */
@Controller
public class RootController {
    @GetMapping("/")
    public String landingPage() {
        return "landingpage";
    }

    @GetMapping("/listimages")
    public String listImages() {
        return "listimages";
    }
}