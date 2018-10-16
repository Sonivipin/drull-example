package com.crosscode.rule.engine.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;
import com.crosscode.rule.engine.model.CrossCodeTableResponse;
import com.crosscode.rule.engine.repository.CrossCodeRuleEngineRepository;

/**
 * 
 * This class is used to implement all crude operations.
 *
 */
@Service
public class DefaultCrossCodeRuleEngineService implements CrossCodeRuleEngineService {

	private final CrossCodeRuleEngineRepository crossCodeRuleEngineRepository;

	@Autowired
	public DefaultCrossCodeRuleEngineService(final CrossCodeRuleEngineRepository crossCodeRuleEngineRepository) {
		this.crossCodeRuleEngineRepository = crossCodeRuleEngineRepository;
	}

	@Override
	public CrossCodeTableResponse saveData(CrossCodeTableResponse crossCodeTableResponse) {
		return crossCodeRuleEngineRepository.save(crossCodeTableResponse);
	}

	@Override
	public List<CrossCodeTableResponse> getAllData() {
		return crossCodeRuleEngineRepository.findAll();
	}

	
	@Override
	public List<CrossCodeTableResponse> getTableDataByRequest(CrossCodeTableRequest crossCodeTableRequest) {
		return crossCodeRuleEngineRepository.findByRequest(crossCodeTableRequest);
	}

	/**
	 * Add some flights at application startup for testing
	 */
	@PostConstruct
	public void loadSourceFile() {
		List<CrossCodeTableResponse> files = Arrays.asList(new CrossCodeTableResponse(1, "Accounts", "source"),
				new CrossCodeTableResponse(2, "HR", "source"), new CrossCodeTableResponse(3, "DeltaClass", "source"),
				new CrossCodeTableResponse(5, "DeltaMain", "source"));
		crossCodeRuleEngineRepository.saveAll(files);
	}

}
