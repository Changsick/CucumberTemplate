package com.song.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.song.model.TestTbVO;
import com.song.repository.TestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
	
	private final TestRepository testRepository;
	
	public List<TestTbVO> getTestData() {
		log.info("###############################");
//		List<TestTbVO> list = testRepository.findByTestCol1(1);
		return testRepository.findAll();
	}

	public TestTbVO saveTestData(TestTbVO testData) {
		return testRepository.save(testData);
	}

}
