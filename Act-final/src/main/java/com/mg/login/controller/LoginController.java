package com.mg.login.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mg.login.models.IMC;
import com.mg.login.models.User;
import com.mg.login.repository.IMCRepository;
import com.mg.login.services.CustomUserDetailsService;

@Controller
public class LoginController {
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private IMCRepository userDataIMC;
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
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
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.user",
	                        "Ya hay un usuario registrado con el nombre de usuario proporcionado");
	    }
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("signup");
	    } else {
	        userService.saveUser(user);
	        modelAndView.addObject("successMessage", "El usuario se ha registrado con éxito");
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
	    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	    modelAndView.setViewName("dashboard");
	    return modelAndView;
	}
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView home() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	/*
	@RequestMapping(value = "/Calular", method = RequestMethod.GET)
	public ModelAndView Calcular() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("calculo");
	    return modelAndView;
	}*/
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public ModelAndView CreateData(IMC imc, ModelMap model) {
	    model.addAttribute("estModel", imc.getEstatura());
	    model.addAttribute("nombModel",imc.getNombre());
	    model.addAttribute("pesoModel", imc.getPeso());
		model.addAttribute("sumaModel", imc.getTheIMC());
		
	
	    userDataIMC.save(imc);
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("Calcular");
	    return modelAndView;
	}
	
	
}
