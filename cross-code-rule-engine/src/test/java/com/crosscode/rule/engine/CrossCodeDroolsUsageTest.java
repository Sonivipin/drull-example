package com.crosscode.rule.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class CrossCodeDroolsUsageTest {

    private static final Logger LOG = LoggerFactory.getLogger(CrossCodeDroolsUsageTest.class);
    
    @Autowired
    private KieSession kieSession;
    
    @Test
    public void sourceFileFileNameUpdated() {
        // Given
    	CrossCodeTableRequest crossCodeTableRequest = new CrossCodeTableRequest("filename", "Account", true);
        
        // When
        // Let´s give the Drools Knowledge-Base an Object, we can then apply rules on
        kieSession.insert(crossCodeTableRequest);
        int ruleFiredCount = kieSession.fireAllRules();
                
        // Then     
        assertEquals("there is 1 rule, thats meets the condition, so there should be 1 applied", 1, ruleFiredCount);
        LOG.debug("Rules checked: {}" + ruleFiredCount);
    }
    
    @Test
    public void sourceFileFileNameNotUpdated() {
    	// Given
    	CrossCodeTableRequest crossCodeTableRequest = new CrossCodeTableRequest("filename", "Account", true);
        
        // When
        // Let´s give the Drools Knowledge-Base an Object, we can then apply rules on
        kieSession.insert(crossCodeTableRequest);
        int ruleFiredCount = kieSession.fireAllRules();
                
        // Then     
        assertEquals("there is 0 rule, thats meets the condition, so there should be 0 applied", 1, ruleFiredCount);
        LOG.debug("Rules checked: {}" + ruleFiredCount);
    }
    
    

}
