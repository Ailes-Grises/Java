package com.example.demo.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({ "/wysiwyg" })
public class RestController {

    @GetMapping
    public String main(Model model) {
        model.addAttribute("articleform", new Articleform());
        return "wysiwyg";
    }
//
//    @PostMapping
//    public String save(Articleform articleform, Model model) {
//        model.addAttribute("articleform", articleform);
//        return "saved";
//    }
}