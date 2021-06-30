package com.vipac.bookingservice.controllers;


import com.vipac.bookingservice.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.vipac.bookingservice.domains.Aula;
import com.vipac.bookingservice.domains.Lezione;
import com.vipac.bookingservice.services.AulaService;


@RestController
public class AulaController {
	
	@Autowired
	private AulaService aulaService;
	final static String authServiceURL = "https://localhost:8443/";
	static final String lecturesServiceURL = "https://localhost:9443/";
	
	@RequestMapping(value = "/prenota", method = RequestMethod.GET)
	public ModelAndView prenota(String lezioneSel, String userJSON, String integrityPrenota){
		ModelAndView modelAndView = new ModelAndView("prenota");
		Gson gson = new Gson();

		String integrityChecksum = org.apache.commons.codec.digest.DigestUtils.sha1Hex(userJSON + lezioneSel);
		if(!integrityPrenota.equals(integrityChecksum)){
			//Checksum non valida - Pagina di errore
			modelAndView.setViewName("error");
		}else{
			Lezione lezione = gson.fromJson(lezioneSel, Lezione.class);
			User currentUser = gson.fromJson(userJSON, User.class);

			Aula aula = aulaService.getByNome(lezione.getAula());

			modelAndView.addObject("lezione",lezione);
			modelAndView.addObject("currentUser", currentUser);
			modelAndView.addObject("aula", aula);
		}
		return modelAndView;
	} 
	
	@RequestMapping(value = "/prenotazione", method = RequestMethod.GET)
    public ModelAndView prenotazione(String currentUser, String idLezione, String riga, String colonna) {
		ModelAndView modelAndView = new ModelAndView("redirect:" + lecturesServiceURL +"updateLezione");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("idLezione", idLezione);
		modelAndView.addObject("riga", riga);
		modelAndView.addObject("colonna", colonna);
		String integrityPrenotazione = org.apache.commons.codec.digest.DigestUtils.sha1Hex(currentUser + idLezione + riga + colonna);
		modelAndView.addObject("integrityPrenotazione", integrityPrenotazione);
		return modelAndView;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + authServiceURL+ "login");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:" + authServiceURL+ "home");
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        return new ModelAndView("redirect:" + authServiceURL+ "logout");
    }
	
	@RequestMapping(value = "/lectures", method = RequestMethod.GET)
    public ModelAndView lectures(String currentUser) {
		ModelAndView modelAndView = new ModelAndView("redirect:" + lecturesServiceURL+ "getAll");
		String integrityAuth = org.apache.commons.codec.digest.DigestUtils.sha1Hex(currentUser);
		modelAndView.addObject("currentUserJSON", currentUser);
		modelAndView.addObject("integrityAuth", integrityAuth);

		return modelAndView;
    }

}
