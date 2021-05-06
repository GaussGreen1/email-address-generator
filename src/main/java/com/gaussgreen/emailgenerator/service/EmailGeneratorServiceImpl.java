package com.gaussgreen.emailgenerator.service;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gaussgreen.emailgenerator.controller.MainControllerImpl;
import com.gaussgreen.emailgenerator.model.RestResponse;
import com.gaussgreen.emailgenerator.utils.Constants;

@Component
public class EmailGeneratorServiceImpl implements EmailGeneratorService {

	private static final Logger logger = LogManager.getLogger(MainControllerImpl.class);

	@Override
	public RestResponse<String> load(List<String> inputNames) {
		RestResponse<String> output = new RestResponse<String>();
		try {
			FileUtils.writeLines(new File(Constants.INPUT_FILE_PATH), inputNames, true);
			logger.info("loadInputNames: " + inputNames.size() + " names have been added to the list to be generated.");
			output.setOutput("OK");
		} catch (Exception e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("loadInputNames error", e.getMessage());
			output.setErrorMessages(err);
			logger.error("loadInputNames error: " + e.getMessage());
		}
		return output;
	}

	@Override
	public RestResponse<List<String>> getList() {
		RestResponse<List<String>> output = new RestResponse<List<String>>();
		try {
			Path filePath = Paths.get(Constants.INPUT_FILE_PATH);
			if (!Files.exists(filePath)) {
				output.setOutput(new ArrayList<String>());
				return output;
			}
			List<String> listFromFile = Files.readAllLines(filePath);
			if (listFromFile == null || listFromFile.isEmpty()) {
				output.setOutput(new ArrayList<String>());
				return output;
			}

			Set<String> set = new LinkedHashSet<String>();
			for (String str : listFromFile) {
				String value = str;
				for (int i = 1; !set.add(value); i++)
					value = str + i;
			}

			List<String> outputList = new ArrayList<String>(set);
			Collections.sort(outputList);
			outputList = outputList.stream().map(x -> x + Constants.EMAIL_DOMAIN).collect(Collectors.toList());
			output.setOutput(outputList);
			logger.info("getAddressList: " + outputList.size() + " email addresses have been generated.");

			new PrintWriter(Constants.INPUT_FILE_PATH).close(); // empty the input list
			logger.info("getAddressList: The input list is now empty.");

		} catch (Exception e) {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("getAddressList error", e.getMessage());
			output.setErrorMessages(err);
			logger.error("getAddressList error: " + e.getMessage());
		}
		return output;
	}

	@Override
	public String getLogs() {
		try {
			Path filePath = Paths.get(Constants.LOG_FILE_PATH);
			if (!Files.exists(filePath))
				return "";
			return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
		} catch (Exception e) {
			logger.error("getLogs error: " + e.getMessage());
			return "getLogs error: " + e.getMessage();
		}
	}

}
