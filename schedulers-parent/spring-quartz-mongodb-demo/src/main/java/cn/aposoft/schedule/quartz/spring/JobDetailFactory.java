/**
 * 
 */
package cn.aposoft.schedule.quartz.spring;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.MethodInvokingJob;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

/**
 * @author Jann Liu
 *
 */
public class JobDetailFactory implements InitializingBean, Serializable {
    private static final long serialVersionUID = 2495708050656947375L;
    private JobDetail jobDetail;

    private String targetMethod;
    private Object targetObject;
    private Class<?> targetClass;

    private String name;

    private String group = Scheduler.DEFAULT_GROUP;

    private boolean concurrent = true;

    public JobDetail getObject() {
        return this.jobDetail;
    }

    /**
     * Set the name of the job.
     * <p>
     * Default is the bean name of this FactoryBean.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the group of the job.
     * <p>
     * Default is the default group of the Scheduler.
     * 
     * @see org.quartz.Scheduler#DEFAULT_GROUP
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Specify whether or not multiple jobs should be run in a concurrent
     * fashion. The behavior when one does not want concurrent jobs to be
     * executed is realized through adding the
     * {@code @PersistJobDataAfterExecution} and
     * {@code @DisallowConcurrentExecution} markers. More information on
     * stateful versus stateless jobs can be found <a href=
     * "http://www.quartz-scheduler.org/documentation/quartz-2.1.x/tutorials/tutorial-lesson-03">
     * here</a>.
     * <p>
     * The default setting is to run jobs concurrently.
     */
    public void setConcurrent(boolean concurrent) {
        this.concurrent = concurrent;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {
        prepare();

        // Consider the concurrent flag to choose between stateful and stateless
        // job.
        Class<?> jobClass = (this.concurrent ? MethodInvokingJob.class : StatefulMethodInvokingJob.class);

        // Build JobDetail instance.
        JobDetailImpl jdi = new JobDetailImpl();
        jdi.setName(name);
        jdi.setGroup(this.group);
        jdi.setJobClass((Class<Job>) jobClass);
        jdi.setDurability(true);
        // jdi.getJobDataMap().put("methodInvoker", this);
        this.jobDetail = jdi;

        postProcessJobDetail(this.jobDetail);
    }

    /**
     * Prepare the specified method. The method can be invoked any number of
     * times afterwards.
     * 
     * @see #getPreparedMethod
     * @see #invoke
     */
    public void prepare() throws ClassNotFoundException, NoSuchMethodException {
    }

    /**
     * Callback for post-processing the JobDetail to be exposed by this
     * FactoryBean.
     * <p>
     * The default implementation is empty. Can be overridden in subclasses.
     * 
     * @param jobDetail
     *            the JobDetail prepared by this FactoryBean
     */
    protected void postProcessJobDetail(JobDetail jobDetail) {
    }

    /**
     * Set the target object on which to call the target method. Only necessary
     * when the target method is not static; else, a target class is sufficient.
     * 
     * @see #setTargetClass
     * @see #setTargetMethod
     */
    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
        if (targetObject != null) {
            this.targetClass = targetObject.getClass();
        }
    }

    /**
     * Return the target object on which to call the target method.
     */
    public Object getTargetObject() {
        return this.targetObject;
    }

    /**
     * Set the name of the method to be invoked. Refers to either a static
     * method or a non-static method, depending on a target object being set.
     * 
     * @see #setTargetClass
     * @see #setTargetObject
     */
    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    /**
     * Return the name of the method to be invoked.
     */
    public String getTargetMethod() {
        return this.targetMethod;
    }

    /**
     * Set the target class on which to call the target method. Only necessary
     * when the target method is static; else, a target object needs to be
     * specified anyway.
     * 
     * @see #setTargetObject
     * @see #setTargetMethod
     */
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Return the target class on which to call the target method.
     */
    public Class<?> getTargetClass() {
        return this.targetClass;
    }

}
