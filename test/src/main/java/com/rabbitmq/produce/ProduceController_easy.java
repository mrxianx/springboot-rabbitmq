package com.rabbitmq.produce;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.confg.RabbitMQConfig;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;

@RestController
public class ProduceController_easy {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	// 拉姆达方式实现线程
	
	@RequestMapping(value="/sendMessage_lamuda",method=RequestMethod.GET)
	public Object sendMessage() {
		
		new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String value = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
                Console.log("send message {}", value);
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
            }
        }).start();
        return "ok";
	}
	
}
