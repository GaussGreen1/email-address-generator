package com.gaussgreen.emailgenerator.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaussgreen.emailgenerator.model.RestResponse;
import com.gaussgreen.emailgenerator.service.EmailGeneratorService;

@RestController
@RequestMapping(value = "")
public class MainControllerImpl implements MainController {

	private static final Logger logger = LogManager.getLogger(MainControllerImpl.class);

	@Autowired
	EmailGeneratorService emailService;

	@Override
	@PostMapping(value = "/generator", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RestResponse<String> loadInputNames(@RequestBody List<String> inputNames) {
		long startTime = System.currentTimeMillis();
		logger.info("loadInputNames: start");

		RestResponse<String> output = emailService.load(inputNames);

		long endTime = System.currentTimeMillis();
		logger.info("loadInputNames ended in " + (endTime - startTime) + " ms");
		return output;
	}

	@Override
	@GetMapping(value = "/generator", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestResponse<List<String>> getAddressList() {
		long startTime = System.currentTimeMillis();
		logger.info("getAddressList: start");

		RestResponse<List<String>> output = emailService.getList();

		long endTime = System.currentTimeMillis();
		logger.info("getAddressList ended in " + (endTime - startTime) + " ms");
		return output;
	}

	@Override
	@GetMapping(value = "/logs")
	public String getLogs() {
		long startTime = System.currentTimeMillis();
		logger.info("getLogs: start");

		String output = emailService.getLogs();

		long endTime = System.currentTimeMillis();
		logger.info("getLogs ended in " + (endTime - startTime) + " ms");
		return output;
	}

}
