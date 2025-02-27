package com.song.stepsdef;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.model.TestTbVO;
import com.song.repository.TestRepository;
import com.song.service.TestService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataViewServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(DataViewServiceTest.class);
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestService testService; // 실제 서비스

    @Autowired
    private TestRepository testRepository; // 실제 Repository

    private List<TestTbVO> expectedData;
    
    private List<TestTbVO> actualData;
    
    @Given("데이터가 있는 상태")
	public void initData() throws Exception {
		testRepository.deleteAll();
		expectedData = new ArrayList<>();
		expectedData.add(testRepository.save(TestTbVO.setAllArgsBuilder()
				.testCol1(1)
				.testCol2("test1")
				.build()));
		expectedData.add(testRepository.save(TestTbVO.setAllArgsBuilder()
				.testCol1(2)
				.testCol2("test2")
				.build()));
	}
	
	@When("클라이언트가 Get 요청으로 데이터를 전송하면")
    public void sendGetReq() throws Exception {
		// Get 요청으로 데이터를 전송
		String responseContent = mockMvc.perform(MockMvcRequestBuilders
        				.get("/api/test1"))
                		.andExpect(status().isOk()) // 응답 상태 확인
                		.andReturn()  // 반환값을 가져옵니다.
                        .getResponse()
                        .getContentAsString();
		
		// 응답 데이터를 실제 데이터로 변환하여 저장
        ObjectMapper objectMapper = new ObjectMapper();
        actualData = objectMapper.readValue(responseContent, objectMapper.getTypeFactory().constructCollectionType(List.class, TestTbVO.class));
    }

    @Then("리턴받은 데이터를 확인한다.")
    public void validReturnData() {
    	
    	// 저장된 데이터가 존재하는지 확인
    	assertThat(actualData).isNotEmpty();
        assertThat(actualData.get(0).getTestCol1()).isEqualTo(1);
        assertThat(actualData.get(0).getTestCol2()).isEqualTo("test1");
    }
}
