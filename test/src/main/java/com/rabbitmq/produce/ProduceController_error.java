package com.rabbitmq.produce;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.confg.RabbitMQConfig;

/*
 * 线程里面，不能使用注解（rabbitTemplate使我们通过注解注入的），spring本身默认bean为单例模式构建， 同时是非线性安全的，因此禁止在thread子类中的注入行为，因此在thread中直接注入的bean是null的
 */

@RestController
public class ProduceController_error extends Thread{
	
	// 特别注意：此方法不能使用，会报空异常，在这里只做记录

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 错误方式，这里的方法也是我们最初的方法，
	 * 存在错误的原因就是：run方法中的rabbitTemplate获取不到值。会报空指针异常
	 */
	@RequestMapping(value="/sendMessage_error",method=RequestMethod.GET)
	public Object sendmessages() {
		Thread thread = new Thread(new ProduceController_error());
		thread.start();
		return "ok";
	}
	
	@Autowired
	public void run() {
		for(int i =0;i<100;i++) {
			SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sm.format(new Date()));
			String value= sm.format(new Date());
			System.out.println("value的值为："+value);
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
		}
	}
}
