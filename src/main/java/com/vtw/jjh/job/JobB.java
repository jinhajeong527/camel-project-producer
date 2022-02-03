package com.vtw.jjh.job;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;


@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobB implements Job {//Scheduler에 의해 실행되는 실제 작업들은 Job Interface의 구현체들이 함
		//Job의 트리거가 발생하면 스케줄러는 JobExecutionContext 객체를 넘겨주고, execute() method를 호출함
		
		@Autowired
		private ProducerTemplate template;//message exchanges를 엔드포인트로 보내는 것을 다양한 방법으로 하는 것을 가능하게 해줌
		
		@Autowired
		private CamelContext camelContext;//single camel routing rule base 모든 조각들을 함께 조합하여 담아두는 Camel의 Runtime 시스템
		//Components, End Points, Route Definition, Type Converter, Data Formats, Registry(Bean 찾는 역할 함), Language 등

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			//JobExecutionContext: Scheduler, Trigger, JobDetail 등 포함하여 Job Instance에 대한 정보 제공하는 객체
			//Job Detail: Job Instance의 자세한 properties 전달함 
			//execution 끝나고나서 Trigger Instance에 주어지는 context에 대한 것 
			//트리거는 파라미터와 데이터를 Trigger의 JobDataMap에 placing함을 통해서 Job에 보낼 수 있다
			
			
			//Routing이 진행되는 동안 Message의 Container 역할 하는 Exchange
			//라우팅 되는 동안에 라이프 사이클이 유지되지만, 그 내부의 메시지는 변경될 수 있음
			Exchange exchange = new DefaultExchange(camelContext);
			
			
			exchange = template.send("direct:bTypeSendRoute", exchange);
			System.out.println("JobB 실행됨");
			
		}//execute end
		
	}//JobA end 

