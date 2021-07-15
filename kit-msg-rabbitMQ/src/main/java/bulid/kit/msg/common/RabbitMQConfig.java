package bulid.kit.msg.common;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String DELAY_EXCHANGE = "fen_guan-delay-exchange";

    public static final String routingPrefix = "fen_guan.";
    public static final String test = "test";
    public static final String transfer = "出款";
    public static final String receive = "收款";
    public static final String transferPay = "出款支付";


    // 测试消息队列
    @Bean(name = "testQueue")
    Queue testQueue() {
        return new Queue(test, false,false,true);
    }
    // 出款订单  消息队列
    @Bean(name = "transferQueue")
    Queue queueTransfer() {
        return new Queue(transfer, false,false,true);
    }

    // 收款订单  消息队列
    @Bean(name = "receiveQueue")
    Queue queueReceive() {return new Queue(receive, false,false,true); }

    // 出款支付订单 消息队列
    @Bean(name = "transferPayQueue")
    Queue queueTransferPay() {return new Queue(transferPay, false,false,true); }

    // declare exchange
    @Bean(name = "DELAY_EXCHANGE")
    DirectExchange exchange() {
        DirectExchange exchange = new DirectExchange(DELAY_EXCHANGE, false, true);
        exchange.setDelayed(true);
        return exchange;
    }

    // binding queue with exchange
    @Bean
    Binding bindingT(@Qualifier("transferQueue") Queue queueTransfer, @Qualifier("DELAY_EXCHANGE") DirectExchange exchange) {
        return BindingBuilder.bind(queueTransfer).to(exchange).with(routingPrefix+ transfer);
    }

    @Bean
    Binding bindingR(@Qualifier( "receiveQueue") Queue queueReceive, @Qualifier("DELAY_EXCHANGE") DirectExchange exchange) {
        return BindingBuilder.bind(queueReceive).to(exchange).with(routingPrefix + receive);
    }

    @Bean
    Binding bindingTP(@Qualifier( "transferPayQueue") Queue queueReceive, @Qualifier("DELAY_EXCHANGE") DirectExchange exchange) {
        return BindingBuilder.bind(queueReceive).to(exchange).with(routingPrefix + transferPay);
    }

    @Bean
    Binding bindingTest(@Qualifier("testQueue") Queue testQueue, @Qualifier("DELAY_EXCHANGE") DirectExchange exchange) {
        return BindingBuilder.bind(testQueue).to(exchange).with(routingPrefix+ test);
    }



}

