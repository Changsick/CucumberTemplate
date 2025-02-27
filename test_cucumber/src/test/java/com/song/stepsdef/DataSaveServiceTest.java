package com.song.stepsdef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.song.model.TestTbVO;
import com.song.repository.TestRepository;
import com.song.service.TestService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataSaveServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(DataSaveServiceTest.class);
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestService testService; // 실제 서비스

    @Autowired
    private TestRepository testRepository; // 실제 Repository

    private TestTbVO expectedData;

	@Given("데이터가 비어있는 상태")
	public void initData() throws Exception {
		testRepository.deleteAll();
	}
	
	@When("클라이언트가 POST 요청으로 데이터를 전송하면")
    public void sendPostReq() throws Exception {
		expectedData = TestTbVO.setAllArgsBuilder()
        .testPk(1L)
        .testCol1(1)
        .testCol2("test1")
        .build();
		
		// POST 요청으로 데이터를 전송
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/testData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testCol1\": 1, \"testCol2\": \"test1\"}"))
                .andExpect(status().isCreated());  // 응답 상태 확인
    }

    @Then("저장된 데이터가 DB에 존재하고 클라이언트에게 리턴된다")
    public void validResult() {
    	// DB에서 데이터를 읽어옴
    	List<TestTbVO> dataFromDb = testService.getTestData();
    	
    	// 저장된 데이터가 존재하는지 확인
    	assertEquals(1, dataFromDb.size());
    	assertEquals(expectedData.getTestCol1(), dataFromDb.get(0).getTestCol1());
        assertEquals(expectedData.getTestCol2(), dataFromDb.get(0).getTestCol2());
    }
}
