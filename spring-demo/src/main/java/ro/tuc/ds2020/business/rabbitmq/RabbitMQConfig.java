package ro.tuc.ds2020.business.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class RabbitMQConfig
{
    private final String TOPIC_EXCHANGE_NAME = "";
    private final String QUEUE_NAME = "device_messages";
    private final String HOST_NAME = "rabitmq";

    @Bean
    Queue queue()
    {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public CachingConnectionFactory connectionFactory()
    {
        return new CachingConnectionFactory(HOST_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter)
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQMessageReceiver receiver)
    {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
