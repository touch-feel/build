package bulid.kit.msg.common;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class RabbitMqService {
    @Autowired
    RabbitTemplate rabbitTemplate;


    @SneakyThrows
    public void test(String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE,RabbitMQConfig.routingPrefix+RabbitMQConfig.test,message);
    }



    /**
     * 发送延迟消息
     * @param orderType
     * @param orderId
     * @param createdTime   订单创建时间, 用来校验是否过期
     */
    public void publishDelayMsg(String orderType, String orderId, String createdTime ){
        String message = "test rabbitMQ msg.. ";

        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE, RabbitMQConfig.routingPrefix + orderType, message, property -> {
            MessageProperties pro = property.getMessageProperties();
            pro.setDelay(10000);

            return property;
        });

    }





    /*======================================   生成并处理消息   ======================================*/

    /**
     *
     * @param createdTime  出款/收款订单创建的时间, +Constant.expireTime判断订单是否超时
     * @return 当前处理时间 & 订单类型 & 订单号 & 订单创建时间
     */
    public static String generateMessage(String orderType, String orderId, String createdTime, Integer expire) {
        DateTime created = DateUtil.parse(createdTime);

        DateTime expireDate = created.offset(DateField.SECOND, expire);

        return orderType + "&" + orderId + "&" + expireDate;
    }

}
