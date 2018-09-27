package com.rabbitmq.produce;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.confg.RabbitMQConfig;

@RestController
public class ProduceController_construction implements Runnable{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public RabbitTemplate rab;

	
	// 写一个包含RabbitTemplate rabbitTemplate的构造函数的，目的是让线程中获取到 rabbitTemplate的值
	public ProduceController_construction(RabbitTemplate rabbitTemplate) {
		rab = rabbitTemplate;
	}
	

	@RequestMapping(value="/sendMessage",method=RequestMethod.GET)
	public Object sendmessages() {
		Thread thread = new Thread(new ProduceController_construction(rabbitTemplate));
		thread.start();
		return "ok";
	}
	
	@Autowired
	public void run() {
		for(int i =0;i<100;i++) {
			SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sm.format(new Date()));
			String value= sm.format(new Date());
			rab.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
		}
	}
	
	
	

}
