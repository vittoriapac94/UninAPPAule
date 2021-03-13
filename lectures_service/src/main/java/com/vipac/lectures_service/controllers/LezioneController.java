package com.vipac.lectures_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vipac.lectures_service.domains.Lezione;
import com.vipac.lectures_service.domains.User;
import com.vipac.lectures_service.services.LezioneService;

@RestController
public class LezioneController {
	
	@Autowired
	private LezioneService lezioneService;
	
	@RequestMapping(value = "/getAllForUser")
	public ModelAndView getAllForUser(@RequestParam String username){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("getAllForUser");
		List<Lezione> list = lezioneService.getAllForUser(username);
		modelAndView.addObject("listaLezioni", list);
		return modelAndView;
	} 

	@RequestMapping(value = "/getAll")
	public ModelAndView getAll(User currentUser, String fullName, String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getAll");
        List<Lezione> list = lezioneService.getAll();
        modelAndView.addObject("listaLezioni", list);
        modelAndView.addObject("fullName", fullName);
        modelAndView.addObject("email", email);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + "http://localhost:8081/login");
	}
	
	@RequestMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("redirect:" + "http://localhost:8081/home");
    }
	
	@RequestMapping(value = "/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:" + "http://localhost:8081/logout");
    }
}
