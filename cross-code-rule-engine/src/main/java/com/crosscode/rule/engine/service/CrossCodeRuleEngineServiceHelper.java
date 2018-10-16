package com.crosscode.rule.engine.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crosscode.rule.engine.model.CrossCodeTableRequest;

@Component
public class CrossCodeRuleEngineServiceHelper {

	private static Logger log = LoggerFactory.getLogger(CrossCodeRuleEngineServiceHelper.class);
	//private static final String CROSS_CODE_SESSION = "CrossCodeSession";

	//private final KieContainer kieContainer;
	private KieSession kieSession;

	@Autowired
	public CrossCodeRuleEngineServiceHelper(final KieSession kieSession) {
		log.info("Initialising a new CrossCode Rule Engine  session.");
		//this.kieContainer = kieContainer;
		this.kieSession = kieSession;
	}

	/**
	 * Create a new session, insert a crossCodeTableRequest's details and fire rules
	 * to determine what kind of action needs to be performed.
	 */
	public CrossCodeTableRequest getDetails(CrossCodeTableRequest crossCodeTableRequest) {
		System.out.println("before - call isUpdated - "+crossCodeTableRequest.isUpdated());
		System.out.println("before - call isValid - "+crossCodeTableRequest.isValid());
		//kieSession = kieSession == null ? kieContainer.newKieSession(CROSS_CODE_SESSION) : kieSession;
		kieSession.insert(crossCodeTableRequest);
		int ruleFiredCount = kieSession.fireAllRules(); 
		System.out.println("Rules fired -"+ruleFiredCount);
		crossCodeTableRequest = findCrossCodeTableRequest(kieSession);
		kieSession.dispose();
		System.out.println("after - call isUpdated- "+crossCodeTableRequest.isUpdated());
		System.out.println("after - call isValid- "+crossCodeTableRequest.isValid());
		return crossCodeTableRequest;
	}

	/**
	 * Search the {@link KieSession} for appropriate action.
	 */
	private CrossCodeTableRequest findCrossCodeTableRequest(final KieSession kieSession) {

		ObjectFilter crossCodeTableRequestFilter = new ObjectFilter() {
			@Override
			public boolean accept(Object object) {
				if (CrossCodeTableRequest.class.equals(object.getClass()))
					return true;
				if (CrossCodeTableRequest.class.equals(object.getClass().getSuperclass()))
					return true;
				return false;
			}
		};

		printFactsMessage(kieSession);

		List<CrossCodeTableRequest> facts = new ArrayList<CrossCodeTableRequest>();
		for (FactHandle handle : kieSession.getFactHandles(crossCodeTableRequestFilter)) {
			facts.add((CrossCodeTableRequest) kieSession.getObject(handle));
		}
		if (facts.size() == 0) {
			return null;
		}
		
		System.out.println("facts column name - "+facts.get(0).getColumnName());
		System.out.println("facts column value- "+facts.get(0).getColumnValue());
		
		// After testing change it to return the list of object.
		return facts.get(0);
	}

	/**
	 * Print out details of all facts in working memory. Handy for debugging.
	 */
	@SuppressWarnings("unused")
	private void printFactsMessage(KieSession kieSession) {
		Collection<FactHandle> allHandles = kieSession.getFactHandles();

		String msg = "\nAll facts:\n";
		for (FactHandle handle : allHandles) {
			msg += "    " + kieSession.getObject(handle) + "\n";
		}
		System.out.println(msg);
	}

}
