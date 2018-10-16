package com.crosscode.rule.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.crosscode.rule.engine.configuration.DroolsAutoConfiguration;

@SpringBootApplication
@Import(DroolsAutoConfiguration.class)
public class CrossCodeRuleEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossCodeRuleEngineApplication.class, args);
	}
}
