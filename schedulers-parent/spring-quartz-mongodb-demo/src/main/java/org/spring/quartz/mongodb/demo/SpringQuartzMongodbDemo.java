package org.spring.quartz.mongodb.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class SpringQuartzMongodbDemo {

	public void run() {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-task.xml");) {
			// Scheduler sc = (Scheduler) ctx.getBean("quartzScheduler");
			// System.out.println(sc.getClass().getName());

			// try {
			// List<String> jgns = sc.getJobGroupNames();
			// for (String jgn : jgns) {
			// GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(jgn);
			//
			// Set<JobKey> keys = sc.getJobKeys(matcher);
			//
			// for (JobKey key : keys) {
			//
			// System.out.println(key.getGroup() + ":" + key.getName());
			// Trigger t1 = newTrigger().withIdentity("trigger1", "group1")
			// .withSchedule(cronSchedule("0/5 * * * * ?")).build();
			// TriggerKey tk = null;
			// List<? extends Trigger> triggers = sc.getTriggersOfJob(key);
			// for (Trigger t : triggers) {
			// System.out.println(t.getClass().getName());
			// tk = t.getKey();
			// sc.rescheduleJob(tk, t1);
			// }
			//
			// // if (key.getName().equals("simpleTaskJobDetail")) {
			//
			// // sc.deleteJob(key);
			// // JobDetail detail = sc.getJobDetail(key);
			// // sc.scheduleJob(detail, t1);
			// // }
			// }
			// }
			// } catch (SchedulerException e1) {
			// e1.printStackTrace();
			// }
			try {
				Thread.sleep(1000 * 300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 主函数方法
	 * 
	 * @param args
	 *            无用入参
	 */
	public static void main(String[] args) {
		new SpringQuartzMongodbDemo().run();
	}
}
