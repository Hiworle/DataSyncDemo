package site.penghao.component;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.penghao.bean.SearchPojo;
import site.penghao.util.HttpUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class Receiver {

    /**
     * 接收消息队列的消息，并同步到 Solr 数据库
     *
     * @param obj 消息
     */
    @RabbitListener(queues = "demoQueue")
    public void receive(Object obj) {
        try {
            Message message = (Message) obj;
            System.out.println(message.toString());
            ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
            ObjectInputStream ois = new ObjectInputStream(bis);
            SearchPojo searchPojo = (SearchPojo) ois.readObject();

            // 将 Queue 中收到的 pojo 同步到 Solr 数据库
            Map<String, String> params = new HashMap<>();
            params.put("id", searchPojo.getId().toString());
            params.put("name", searchPojo.getName());
            params.put("price", searchPojo.getPrice().toString());
            HttpUtil.doPost("http://localhost:8090/insert", params);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
