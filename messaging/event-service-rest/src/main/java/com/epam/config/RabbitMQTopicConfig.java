package com.epam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitMQTopicConfig {

    @Bean
    public Queue queueCreate() {
        return new Queue("create-event-request");
    }

    @Bean
    public Queue queueUpdate() {
        return new Queue("update-event-request");
    }

    @Bean
    public Queue queueDelete() {
        return new Queue("delete-event-request");
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange-topic");
    }

    @Bean
    public Binding bindingCreate(TopicExchange topicExchange) {
        return BindingBuilder.bind(queueCreate()).to(topicExchange).with("create-binding");
    }

    @Bean
    public Binding bindingUpdate(TopicExchange topicExchange) {
        return BindingBuilder.bind(queueUpdate()).to(topicExchange).with("update-binding");
    }

    @Bean
    public Binding bindingDelete(TopicExchange topicExchange) {
        return BindingBuilder.bind(queueDelete()).to(topicExchange).with("delete-binding");
    }

    @Bean("Jackson2JsonMessageConverter")
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

}
