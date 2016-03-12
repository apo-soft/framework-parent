package org.quartz.jobstore.mysql.demo;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class QuartzJobStoreMysqlExample {
	/**
	 * 运行实际动作
	 */
	public void run() {
		Logger log = LoggerFactory.getLogger(QuartzJobStoreMysqlExample.class);
		log.info("------- Initializing -------------------");

		// First we must get a reference to a scheduler
		Scheduler sched = null;
		try {
			SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
			sched = sf.getScheduler();
			if (sched == null) {
				log.error("sched is null.");
				return;
			}
		} catch (NoClassDefFoundError e) {
			log.error(
					" Unable to load a class - most likely you do not have jta.jar on the classpath. If not present in the examples/lib folder, please "
							+ "add it there for this sample to run.",
					e);
			return;
		} catch (SchedulerException e) {
			log.error(" Meets schedule exception.", e);
			return;
		}

		log.info("------- Initialization Complete --------");
		JobKey jobKey = new JobKey("job1", "group1");
		try {
			JobDetail jobDetail = sched.getJobDetail(jobKey);
			if (jobDetail == null) {
				// define the job and tie it to our HelloJob class
				JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
				// "0/20 * * * * ?" 第一位表示秒, 0/20 表示 从0秒开始,步进为20秒
				CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
						.withSchedule(cronSchedule("0/3 * * * * ?")).build();
				try {
					Date ft = sched.scheduleJob(job, trigger);
					log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
							+ trigger.getCronExpression());
				} catch (NullPointerException e) {
					log.error("NullPointer Exception:", e);
					return;
				}
			}

			log.info("------- Starting Scheduler ----------------");

			// All of the jobs have been added to the scheduler, but none of the
			// jobs
			// will run until the scheduler has been started
			sched.start();

			log.info("------- Started Scheduler -----------------");

			log.info("------- Waiting five minutes... ------------");
			try {
				// wait five minutes to show jobs
				Thread.sleep(600L * 1000L);
				// executing...
			} catch (Exception e) {
				//
			}

			log.info("------- Shutting Down ---------------------");

			sched.shutdown(true);

			log.info("------- Shutdown Complete -----------------");

			SchedulerMetaData metaData = sched.getMetaData();
			log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
		} catch (SchedulerException e) {
			log.error(" Meets schedule exception.", e);
		}

	}

	public static void main(String[] args) {
		new QuartzJobStoreMysqlExample().run();
	}
}
