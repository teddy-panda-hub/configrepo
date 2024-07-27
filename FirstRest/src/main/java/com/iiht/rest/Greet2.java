package com.iiht.rest;

import java.nio.charset.Charset;
//import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//API
//endpoint
//Request handler
@RestController
@RequestMapping("/rest/api.2.0")
public class Greet2 {
	
	@GetMapping
	@RequestMapping("/welcome")
	public ResponseEntity<String> message() {
		
		ResponseEntity<String> response=new ResponseEntity<>("OLA! Commo Sava",HttpStatus.OK);
		return response;
	}
	
	@GetMapping
	@RequestMapping("/time")
	public ResponseEntity<String> getType() {
		
		//ResponseEntity<String> response=new ResponseEntity<>(Calendar.getInstance().getTime()+"",HttpStatus.ACCEPTED);
		//return response;
		return ResponseEntity.status(HttpStatus.OK).body(Calendar.getInstance().getTime()+"");
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
	public ResponseEntity<String> genderMessage(@RequestHeader HttpHeaders headers, @PathVariable String gender, @PathVariable String name) {
		
		Set<String> set=headers.keySet();
		Iterator<String> keys=set.iterator();
		@SuppressWarnings("unused")
		String keyStr="\n";
		while(keys.hasNext())
			keyStr+=keys.next()+"\n";
		
		String keyValueStr=null;
		Set<Entry<String,List<String>>> entries=headers.entrySet();
		Iterator<Entry<String, List<String>>> iter2=entries.iterator();
		while(iter2.hasNext())
				keyValueStr+=iter2.next()+"<br/>"+"\n";
		
		HttpHeaders headers2= new HttpHeaders();
		Charset charset= Charset.defaultCharset();
		List<Charset> list=new ArrayList<Charset>();
		list.add(charset);
		headers2.setAcceptCharset(list);
		headers2.setContentType(MediaType.TEXT_HTML);
		headers2.setContentLength(keyValueStr.length());
		headers2.set("ust","GammaTraining#6");
		
		return ResponseEntity.status(HttpStatus.OK).headers(headers2).body(keyValueStr) ;
		//return keyValueStr;
		//return keyStr;
	}
	
	@GetMapping
	@RequestMapping(value="/locale")
	public ResponseEntity<String> locale(@RequestHeader HttpHeaders headers) {
		
		/*
		 * if(member.getGender().equals("Female")) return
		 * ResponseEntity.ok("Welcome Miss. "+member.getName()); else
		 * if(member.getGender().equals("Male")) return
		 * ResponseEntity.ok("Welcome Mr. "+member.getName()); return
		 * ResponseEntity.ok("Welcome "+member.getName());
		 */
		HttpHeaders headers2= new HttpHeaders();
		//Locale locale=Locale.FRENCH;
		/*List<Locale> l2=new ArrayList();
		l2.add(Locale.FRENCH);
		l2.add(Locale.JAPANESE);
		l2.add(Locale.GERMAN);*/
		Locale l= new Locale("Thai","Thailand");
		List<Locale> l2=headers.getAcceptLanguageAsLocales();
		headers2.setAcceptLanguageAsLocales(l2);
		return ResponseEntity.status(HttpStatus.OK).headers(headers2).body(l.getDisplayLanguage(l)+" ");
	}

}
