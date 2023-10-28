package com.mathewzvk.springboot.validationdemo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mathewzvk.springboot.validationdemo.model.Customer;

import jakarta.validation.Valid;


@Controller
public class CustomerController {
	
	
	@InitBinder
	public void initBinder(WebDataBinder theDataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		
		theDataBinder.registerCustomEditor(String.class, editor);
		
	}

	
	@GetMapping("/")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	@PostMapping("/processform")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		}else {
			return "customer-confirm";
		}
	}
	
}
