package com.crosscode.rule.engine.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;
import com.crosscode.rule.engine.model.CrossCodeTableResponse;
import com.crosscode.rule.engine.service.CrossCodeRuleEngineService;
import com.crosscode.rule.engine.service.CrossCodeRuleEngineServiceHelper;

@RestController
@RequestMapping("/crossCode")
public class CrossCodeRuleEngineController {

  final static Logger LOGGER = LogManager.getLogger(CrossCodeRuleEngineController.class);

  private final CrossCodeRuleEngineService crossCodeRuleEngineService;
  private final CrossCodeRuleEngineServiceHelper crossCodeRuleEngineServiceHelper;

  @Autowired
  public CrossCodeRuleEngineController(final CrossCodeRuleEngineService crossCodeRuleEngineService, final CrossCodeRuleEngineServiceHelper crossCodeRuleEngineServiceHelper) {
    this.crossCodeRuleEngineService = crossCodeRuleEngineService;
    this.crossCodeRuleEngineServiceHelper = crossCodeRuleEngineServiceHelper;
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CrossCodeTableResponse> addData(@RequestBody final CrossCodeTableResponse crossCodeTableResponse) {
    crossCodeRuleEngineService.saveData(crossCodeTableResponse);
    LOGGER.debug("Added: " + crossCodeTableResponse);
   
    return new ResponseEntity<CrossCodeTableResponse>(crossCodeTableResponse, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/searchCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CrossCodeTableResponse>> getTableDataByRequest(
                  @RequestBody  CrossCodeTableRequest crossCodeTableRequest) {

    List<CrossCodeTableResponse> crossCodeTabletResponses = crossCodeRuleEngineService.getTableDataByRequest(crossCodeTableRequest);
    if (crossCodeTabletResponses.isEmpty()) {
      LOGGER.debug("CrossCodeTableResponse does not exists");
      return new ResponseEntity<List<CrossCodeTableResponse>>(HttpStatus.NO_CONTENT);
    } else {
      LOGGER.debug("Found " + crossCodeTabletResponses.size() + " data");
      LOGGER.debug(crossCodeTabletResponses);
      LOGGER.debug(Arrays.toString(crossCodeTabletResponses.toArray()));
    }
    
    crossCodeTableRequest =  crossCodeRuleEngineServiceHelper.getDetails(crossCodeTableRequest);
    System.out.println("isValid - "+ crossCodeTableRequest.isValid());

    return new ResponseEntity<List<CrossCodeTableResponse>>(crossCodeTabletResponses, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CrossCodeTableResponse>> getAllData() {

    List<CrossCodeTableResponse> crossCodeTabletResponses = crossCodeRuleEngineService.getAllData();
    if (crossCodeTabletResponses.isEmpty()) {
      LOGGER.debug("CrossCodeTableResponse does not exists");
      return new ResponseEntity<List<CrossCodeTableResponse>>(HttpStatus.NO_CONTENT);
    } else {
      LOGGER.debug("Found " + crossCodeTabletResponses.size() + " data changes");
      LOGGER.debug(crossCodeTabletResponses);
      LOGGER.debug(Arrays.toString(crossCodeTabletResponses.toArray()));
    }

    return new ResponseEntity<List<CrossCodeTableResponse>>(crossCodeTabletResponses, HttpStatus.OK);
  }

}
