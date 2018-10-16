package com.crosscode.rule.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;
import com.crosscode.rule.engine.model.CrossCodeTableResponse;

@Repository
public interface CrossCodeRuleEngineRepository extends JpaRepository<CrossCodeTableResponse, Long> {

	@Query("select cr from CrossCodeTableResponse cr where cr.filename = :#{#crossCodeTableRequest.columnValue}")
	List<CrossCodeTableResponse> findByRequest(
			@Param("crossCodeTableRequest") final CrossCodeTableRequest crossCodeTableRequest);

}
