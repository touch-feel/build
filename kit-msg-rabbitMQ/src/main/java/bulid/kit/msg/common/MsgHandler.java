package bulid.kit.msg.common;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgHandler {
    @Autowired
    RabbitMqService rabbitMqService;


    @RabbitListener(queues = {RabbitMQConfig.test})
    @SneakyThrows
    public void testRabbitMQ(Message messageObj){
        System.out.println("\n\n\n\n\n");

        System.out.println("@处理: "+new String(messageObj.getBody()));

    }


    @RabbitListener(queues = {RabbitMQConfig.transfer,RabbitMQConfig.receive})
    @SneakyThrows
    public void handleMerchantOrder(Message messageObj){


        System.out.println(new DateTime()+"\n\n\n\n\n\n @RabbitListener:  处理商户出款订单");

        // 消息格式:  "订单类型&订单号&创建时间"
        String message = new String(messageObj.getBody());
        message = StrUtil.str(messageObj.getBody(), CharsetUtil.UTF_8);
        System.out.println(message);


    }


}
