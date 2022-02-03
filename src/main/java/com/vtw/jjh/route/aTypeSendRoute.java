package com.vtw.jjh.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.vtw.jjh.vo.Order;

@Component
public class aTypeSendRoute extends RouteBuilder {
	

	@Override
	public void configure() throws Exception {
		//제네릭이 Order class형인 List 선언
		List<Order> list = new ArrayList<>();
		
		list.add(new Order(1,"COFFEE", 4100));
		list.add(new Order(2,"BREAD", 3200));
		list.add(new Order(3,"BUTTER", 500));
		
		Integer size = list.size();
		
		
		onCompletion()
		.to("direct:historySendRoute")
		.log("sent to direct:historySendRoute from aTypeSendRoute");
		 
		//JSON형태의 데이터 생성하여 data-a-jh 토픽으로 보내주기
		from("direct:aTypeSendRoute")
			.routeId("aTypeSendRoute")
			.setBody(constant(list))
			.marshal().json(JsonLibrary.Jackson)
			.log("after json marshal : ${body}")
			.to("kafka:data-a-jh")
			.log("sent to data-a-jh topic");

		  
		
	}

}
