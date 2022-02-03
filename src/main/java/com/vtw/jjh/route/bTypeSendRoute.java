package com.vtw.jjh.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.vtw.jjh.vo.Order;


@Component
public class bTypeSendRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception { 
		
		
		List<Order> coffeeList = new ArrayList<>();
	
		coffeeList.add(new Order(1,"AMERICANO", 4100));
		coffeeList.add(new Order(2,"LATTE", 4600));
		coffeeList.add(new Order(3,"ESPRESSO", 3800));
		
		//완료시 historySendRoute로 보내줌
		onCompletion()
		.to("direct:historySendRoute")
		.log("sent to direct:historySendRoute from bTypeSendRoute");
		
		//xml형태 데이터 생성하여 data-b-jh 토픽으로 보내기
		from("direct:bTypeSendRoute")
			.routeId("bTypeSendRoute")
			.setBody(constant(coffeeList))
			.marshal()
			.jacksonxml(true)
			.to("kafka:data-b-jh")
			.log("sent to topic data-b-jh");
		
	}

}
