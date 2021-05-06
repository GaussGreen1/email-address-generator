package com.gaussgreen.emailgenerator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.gaussgreen.emailgenerator.model.RestResponse;

public interface MainController {

	RestResponse<String> loadInputNames(@RequestBody List<String> inputNames);
	RestResponse<List<String>> getAddressList();
	String getLogs();
	
}
