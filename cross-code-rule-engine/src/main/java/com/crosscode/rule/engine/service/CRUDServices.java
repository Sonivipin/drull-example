package com.crosscode.rule.engine.service;

import java.util.List;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;

public interface CRUDServices<E> {

	E saveData(final E entity);

	List<E> getAllData();

	List<E> getTableDataByRequest(final CrossCodeTableRequest entity);
}
