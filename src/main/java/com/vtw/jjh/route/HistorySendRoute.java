package com.vtw.jjh.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.vtw.jjh.processor.DataHistoryProcessor;


@Component
public class HistorySendRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		
		//aTypeSendRoute, bTypeSendRoute 실행 후 송신 이력 정보 프로세스해서 jh-data-history 토픽으로 송신함
		from("direct:historySendRoute")
			.routeId("historySendRoute")
			.setProperty("category", constant("receive"))
			.process(DataHistoryProcessor.NAME)
			.marshal().json()//POJO를 JSON으로
			.to("kafka:jh-data-history")
			.log("sent to topic:jh-data-history");
		
	}//configure end
	
}//HistorySendRoute end 