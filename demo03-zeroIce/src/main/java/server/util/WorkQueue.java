/**  
 * Filename:    WorkQueue.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     Fsmeeting 
 * @author:     Scott  
 * @version:    1.0  
 * Create at:   2015年6月8日 上午11:05:30  
 *  
 * Modification History:  
 * Date           Author       Version      Description  
 * ------------------------------------------------------------------  
 * 2015年6月8日    Scott       1.0        1.0 Version  
 */

package server.util;

import com.hxl.server.job.AbstractJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: WorkQueue
 * @Description: TODO
 * @author Scott
 * @date 2015年6月8日 上午11:05:30
 * 
 */
public class WorkQueue {

    private static int maxWaitingJobs;
    private static final LinkedList<AbstractJob> jobList = new LinkedList<AbstractJob>();
    private static final ExecutorService workerPool = Executors.newCachedThreadPool();

    private static final Logger logger = LoggerFactory.getLogger(WorkQueue.class);

    /**
     * 
     * @param workingThreads workingThreads
     * @param maxWaitingJobs maxWaitingJobs
     */
    public WorkQueue(int workingThreads, int maxWaitingJobs) {

        WorkQueue.maxWaitingJobs = maxWaitingJobs;

        for (int i = 0; i < workingThreads; i++) {
            Worker worker = new Worker();
            workerPool.execute(worker);
        }
    }

    /**
     * 
     * @return boolean
     */
    public static boolean isBusy() {
        GlobalVar.requestNumbers++;
        if (jobList.size() > maxWaitingJobs) {
            GlobalVar.rejectNumbers++;
            logger.info("system is busy... current waiting jobs : " + jobList.size());
            return true;
        }

        return false;
    }

    /**
     * 
     * @param job job
     */
    public static void addJob(AbstractJob job) {
        synchronized (jobList) {
            jobList.addLast(job);
            GlobalVar.receiveNumbers++;
            int size = jobList.size();
            if (size > 100) {  // 队列大于100才打印日志
                logger.info("add job success; current jobs:" + size);
            }            
            jobList.notifyAll();
        }
    }


    private class Worker implements Runnable {

        @Override
        public void run() {

            AbstractJob job;
            while (true) {
                synchronized (jobList) {
                    while (jobList.isEmpty()) {
                        try {
                            jobList.wait();
                        } catch (InterruptedException ignored) {
                            logger.error("", ignored);
                            Thread.currentThread().interrupt();
                        }
                    }
                    job = (AbstractJob) jobList.removeFirst();
                }

                try {
                    job.execute();
                } catch (Exception ex) {
                    logger.error("execute job Exception", ex);
                }
            }
        }

    }

    public int getMaxJobsSize() {
        return maxWaitingJobs;
    }

}
