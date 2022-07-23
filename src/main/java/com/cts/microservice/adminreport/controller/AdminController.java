package com.cts.microservice.adminreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.adminreport.model.PostDTO;
import com.cts.microservice.adminreport.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminServiceImpl;
	
	@GetMapping("/admin/post/reported")
	public PostDTO[] reportedPosts() {
		return adminServiceImpl.getAllReportedPosts();
	}
	
	
	@DeleteMapping("/admin/{id}")
	public void deletePost(@PathVariable Long id) {
		adminServiceImpl.deleteThePost(id);
	}
	
}
