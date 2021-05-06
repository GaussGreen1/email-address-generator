package com.gaussgreen.emailgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gaussgreen.emailgenerator.model.RestResponse;
import com.gaussgreen.emailgenerator.service.EmailGeneratorService;
import com.gaussgreen.emailgenerator.utils.Constants;

@SpringBootTest
class EmailGeneratorApplicationTests {
	
	@Autowired
	EmailGeneratorService emailService;

	@Test
	void generateAddressesTest() {
		List<String> inputNames = Arrays.asList("name.surname", "name.surname", "other.other");
		List<String> expectedAddresses = Arrays.asList("name.surname"+Constants.EMAIL_DOMAIN, "name.surname1"+Constants.EMAIL_DOMAIN, "other.other"+Constants.EMAIL_DOMAIN);
		emailService.load(inputNames);
		RestResponse<List<String>> output = emailService.getList();
		assertEquals(output.getOutput().containsAll(expectedAddresses), true);
	}

}
