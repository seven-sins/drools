package com.drools.demo.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drools.demo.po.Person;

@RestController
public class TestController {
	
	@Autowired
	KieSession kieSession;
	
	@GetMapping("/test")
	public Object test(){
		Person person = new Person();
		person.setAge(66);
		person.setName("曹操");
		kieSession.insert(person);
		kieSession.getAgenda().getAgendaGroup("temp01").setFocus();
		
		int i = kieSession.fireAllRules();
		System.out.println(i);
		// 在规则中修改了age
		System.out.println(person.getAge());
		
		return 1;
	}
}
