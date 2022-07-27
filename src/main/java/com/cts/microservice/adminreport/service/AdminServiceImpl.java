package com.cts.microservice.adminreport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.microservice.adminreport.model.PostDTO;

@Service
public class AdminServiceImpl implements AdminService {

  @Override
	public PostDTO[] getAllReportedPosts() {
		String uri ="http://localhost:8082/post/reported";
		RestTemplate restTemplate = new RestTemplate();
		PostDTO[] result = restTemplate.getForObject(uri,PostDTO[].class);
		return result;
	}

	@Override
	public void deleteThePost(Long id) {
		String uri ="http://localhost:8082/post/delete/{id}";
		Map<String , Long > param = new HashMap<String,Long>();
		RestTemplate restTemplate = new RestTemplate();
		param.put("id",id);
		restTemplate.delete(uri,param);
	}
	
	@Override
	public void removeThePost(Long id) {
		String uri ="http://localhost:8082/post/unreport/{id}";
		Map<String , Long > param = new HashMap<String,Long>();
		RestTemplate restTemplate = new RestTemplate();
		param.put("id",id);
		restTemplate.put(uri, null ,param);
	}

}
