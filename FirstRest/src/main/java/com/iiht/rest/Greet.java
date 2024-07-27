package com.iiht.rest;

import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//API
//endpoint
//Request handler
@RestController
@RequestMapping("/rest/api.1.0")
public class Greet {
	
	@GetMapping
	@RequestMapping("/welcome")
	public String message() {
		
		return "OLA! Commo Sava";
	}
	
	@GetMapping
	@RequestMapping("/time")
	public String getType() {
		
		return Calendar.getInstance().getTime()+"";
	}
	
	@GetMapping
	@RequestMapping("/{gender}")
	public String genderMessage(@PathVariable String gender) {
		
		if(gender.equals("Female"))
			return "Welcome Miss";
		else if(gender.equals("Male"))
			return "Welcome Mr";
		return "Welcome";
	}
	
	@GetMapping
	@RequestMapping("/namegender/{name}/{gender}")
	public String genderMessage(@PathVariable String gender, @PathVariable String name) {
		
		if(gender.equals("Female"))
			return "Welcome Miss"+" "+name;
		else if(gender.equals("Male"))
			return "Welcome Mr"+" "+name;
		return "Welcome"+" "+name;
	}

}
