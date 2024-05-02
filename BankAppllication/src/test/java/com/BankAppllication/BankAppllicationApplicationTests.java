package com.BankAppllication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.application")
@SpringBootTest(classes = BankAppllicationApplicationTests.class)
class BankAppllicationApplicationTests {

	@Test
	void contextLoads() {
		
	} 

}
