package com.vipac.authentication_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vipac.authentication_service.domains.User;
import com.vipac.authentication_service.services.CustomUserDetailService;

@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String fromView) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("fromView", fromView);
        return modelAndView;
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        } else {
        	userExists = userService.findUserByMatricola(user.getMatricola());
        	if (userExists != null) {
                bindingResult
                        .rejectValue("matricola", "error.user",
                                "There is already a user registered with the matricola provided");
        	}
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullname());
        modelAndView.addObject("matricola", user.getMatricola());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
    
    @RequestMapping(value = "/lectures", method = RequestMethod.GET)
	public ModelAndView lectures() {
    	ModelAndView modelAndView = new ModelAndView("redirect:" + "http://localhost:8082/getAll");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
    	modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", user.getFullname());
        modelAndView.addObject("matricola", user.getMatricola());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("roles",user.getRoles());
	    return modelAndView;
	}
    
    
    /*@GetMapping("/lectures")
    public RedirectView redirectme(final RedirectAttributes redirectAttributes) {
        // the following attribute is a ModelAttribute
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
        redirectAttributes.addFlashAttribute("currentUser", user);
        redirectAttributes.addFlashAttribute("fullName", user.getFullname());
        redirectAttributes.addFlashAttribute("matricola", user.getMatricola());
        redirectAttributes.addFlashAttribute("email", user.getEmail());
        redirectAttributes.addFlashAttribute("corsoLaurea", user.getCorsoLaurea());
        redirectAttributes.addFlashAttribute("annoImmatricolazione", user.getAnnoImmatricolazione());
        final RedirectView redirectView = new RedirectView("http://localhost:8082/getAll", true);
        // the following attributes are request-parameter (dynamic Attributes)
        //redirectAttributes.addAttribute("user", user);
        //redirectView.getAttributesMap().put("user", user);
        return redirectView;
    }*/

}