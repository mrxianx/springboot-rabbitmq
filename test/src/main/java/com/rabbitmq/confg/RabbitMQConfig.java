package com.rabbitmq.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitMQConfig {
	
	public final static String QUEUE_NAME = "spring-boot-queue";
    public final static String EXCHANGE_NAME = "spring-boot-exchange";
    public final static String ROUTING_KEY = "spring-boot-key";
    
    
    // 创建队列
    @Bean
    public Queue queue() {
		return new Queue(QUEUE_NAME);
    }

    // 创建一个topic类型的交换器
    @Bean
    public TopicExchange exchange() {
    	return new TopicExchange(EXCHANGE_NAME);
    }
    
    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding(Queue queue,TopicExchange exchange) {
    	return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    
    // 创建连接工厂（主要配置连接信息）,可以在properties 中设置配置内容
    @Bean
    public ConnectionFactory connectionFactory() {
    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.172.129", 5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
		return connectionFactory;
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    
}
