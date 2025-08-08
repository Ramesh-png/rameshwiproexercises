	package com.example.mobilecurd.controller;
	
	
	import com.example.mobilecurd.model.Mobile;
	import com.example.mobilecurd.service.MobileService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;
	 
	import java.util.List;
	 
	@RestController
	@RequestMapping("/mobiles")
	public class MobileController {
	 
	    @Autowired
	    private MobileService mobileService;
	 
	    @PostMapping
	    public Mobile createMobile(@RequestBody Mobile mobile) {
	        return mobileService.saveMobile(mobile);
	    }
	 
	    @GetMapping
	    public List<Mobile> getAllMobiles() {
	        return mobileService.getAllMobiles();
	    }
	 
	    @GetMapping("/{id}")
	    public Mobile getMobileById(@PathVariable int id) {
	        return mobileService.getMobileById(id).orElse(null);
	    }
	 
	    @PutMapping("/{id}")
	    public Mobile updateMobile(@PathVariable int id, @RequestBody Mobile mobile) {
	        return mobileService.updateMobile(id, mobile);
	    }
	 
	    @DeleteMapping("/{id}")
	    public String deleteMobile(@PathVariable int id) {
	        mobileService.deleteMobile(id);
	        return "Mobile deleted with id: " + id;
	    }
	}
	 