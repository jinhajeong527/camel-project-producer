package com.vtw.jjh.schedule;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtw.jjh.job.JobA;
import com.vtw.jjh.job.JobB;



@Component
public class AgentScheduleService {
	
	@Autowired
	Scheduler scheduler;
	
	@PostConstruct
	public void createTrigger() throws SchedulerException {
		
		JobDetail jobADetail = JobBuilder.newJob(JobA.class)//JobA.class 지정한  Job을
									.withIdentity("JobForA", "ABGroup")//이름과 그룹이름 지정해서
									.withDescription("job for aTypeSendRoute")//설명 추가
									.build();//Job 만듦

		Trigger aTrigger = TriggerBuilder.newTrigger()//새로운 Trigger
									.withIdentity("TriggerForA", "ABGroup")//이름과 그룹이름 지정
									.withDescription("trigger for aTypeSendRoute")
									.startNow()//start함
									.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))//스케줄 지정: 30초마다 반복함
									.build();//트리거 만듦
		
		JobDetail jobBDetail = JobBuilder.newJob(JobB.class)//JobA.class 지정한  Job을
									.withIdentity("JobForB", "ABGroup")//이름과 그룹이름 지정해서
									.withDescription("job for bTypeSendRoute")//설명 추가
									.build();//Job 만듦

		Trigger bTrigger = TriggerBuilder.newTrigger()//새로운 Trigger
									.withIdentity("TriggerForB", "ABGroup")//이름과 그룹이름 지정
									.withDescription("trigger for bTypeSendRoute")
									.startNow()//start함
									.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))//스케줄 지정: 60초마다 반복함
									.build();//트리거 만듦
		
		//등록된 Job의 Key와 충돌할 경우 기존의 Key를 삭제
		if(scheduler.checkExists(jobADetail.getKey())) {
			scheduler.deleteJob(jobADetail.getKey());
		}//if end 
		if(scheduler.checkExists(jobBDetail.getKey())) {
			scheduler.deleteJob(jobBDetail.getKey());
		}//if end 

		scheduler.scheduleJob(jobADetail, aTrigger); //Scheduler에 해당 job을 해당 trigger에 따라 실행하도록 추가 함 
		scheduler.scheduleJob(jobBDetail, bTrigger);
		
		try {
			Thread.sleep(65L * 1000L);//65초 동안 Scheduler가 실행되도록 시간을 줌
		} catch (Exception e) {
		}//try~catch end
		
	}//main end 
		
}//AgentScheduleService end
	


