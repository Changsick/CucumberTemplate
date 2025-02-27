package com.song.stepsdef;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.song.TestCucumberApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = TestCucumberApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberConfiguration {

}
