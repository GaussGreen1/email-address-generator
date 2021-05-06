package com.gaussgreen.emailgenerator.service;

import java.util.List;

import com.gaussgreen.emailgenerator.model.RestResponse;

public interface EmailGeneratorService {

	RestResponse<String> load(List<String> inputNames);

	RestResponse<List<String>> getList();

	String getLogs();

}
