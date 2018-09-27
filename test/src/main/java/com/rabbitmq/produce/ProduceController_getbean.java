//package com.rabbitmq.produce;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rabbitmq.confg.RabbitMQConfig;
//import com.rabbitmq.util.SpringContextUtils;
//
//@RestController
//public class ProduceController_getbean extends Thread {
//
//	// 还可以通过springcontext getbean
//
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//	
//	@RequestMapping(value="/sendMessage_getbean",method=RequestMethod.GET)
//	public Object sendmessages() {
//		Thread thread = new Thread(new ProduceController_error());
//		thread.start();
//		return "ok";
//	}
//	
//	@Autowired
//	public void run() {
//		for(int i =0;i<100;i++) {
//			SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(sm.format(new Date()));
//			String value= sm.format(new Date());
//			System.out.println("value的值为："+value);
//			RabbitTemplate rab = (RabbitTemplate) SpringContextUtils.getBean("rabbitTemplate");
//			System.out.println(rab);
//			rab.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
//		}
//	}
//
//}
