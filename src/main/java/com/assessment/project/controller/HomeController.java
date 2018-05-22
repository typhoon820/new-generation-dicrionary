package com.assessment.project.controller;

import com.assessment.project.model.LevelEntity;
import com.assessment.project.model.UserEntity;
import com.assessment.project.service.LevelService;
import com.assessment.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;
    @Autowired
    LevelService levelService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome(){

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loggedUser = userService.findByLogin(auth.getName());

        model.addObject("loggedUser", loggedUser);
        model.addObject("levels",levelService.findAllLevels());
        if (loggedUser.getLevel() == null) model.addObject("userLvl", "Not set");
        else model.addObject("userLvl", loggedUser.getLevel().getLevel());

        model.addObject("welcomeMsg", "Hello, "+ loggedUser.getLogin());
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/changelevel",method = RequestMethod.POST)
    public ModelAndView changeLvl(@ModelAttribute("loggedUser") UserEntity loggedUser, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity lUser = userService.findByLogin(auth.getName());
        if (bindingResult.hasErrors()) model.setViewName("redirect:/home");
        else{
            lUser.setLevel(loggedUser.getLevel());
            logger.info("name:" + loggedUser.getLogin() + " mail : " + loggedUser.getEmail() +" level" + loggedUser.getLevel().getLevel());
            userService.updateUser(lUser);
            model.setViewName("redirect:/home");
        }
        return model;
    }


}
