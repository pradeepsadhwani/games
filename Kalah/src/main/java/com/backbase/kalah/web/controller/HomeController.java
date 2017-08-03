package com.backbase.kalah.web.controller;

/**
 * Created by f553457 on 11/7/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by js on 9/21/16.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("index");
    }

}
