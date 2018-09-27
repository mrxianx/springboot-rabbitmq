package com.rabbitmq.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.confg.RabbitMQConfig;

import cn.hutool.core.lang.Console;

@Component
public class Consumer {
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void consumeMessage(String Message) {
		Console.log("consume message {}", Message);
	}

}
