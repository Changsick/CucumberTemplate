package com.song.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.model.TestTbVO;
import com.song.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class TestRestController {
	
	private final TestService testService;

	@GetMapping("/test1")
	public ResponseEntity<List<TestTbVO>> test1() { // @RequestParam String val1
		List<TestTbVO> dataList = testService.getTestData();
		log.info("dataList : " + dataList);
		return new ResponseEntity<>(dataList, HttpStatus.OK);
	}
	
	@PostMapping("/testData")
    public ResponseEntity<TestTbVO> createTestData(@RequestBody TestTbVO testData) {
        TestTbVO savedData = testService.saveTestData(testData);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }
	
}
