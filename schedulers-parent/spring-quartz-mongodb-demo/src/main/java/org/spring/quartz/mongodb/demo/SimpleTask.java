/**
 * 
 */
package org.spring.quartz.mongodb.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jann Liu
 *
 */
public class SimpleTask {

	private static final Logger logger = LoggerFactory.getLogger(SimpleTask.class);

	public void execute() {
		logger.debug("debug before execute");
		logger.info("execute simple task.");
	}
}
