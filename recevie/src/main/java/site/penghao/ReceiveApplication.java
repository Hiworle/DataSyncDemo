package site.penghao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监听队列情况，一旦收到新的 SearchPojo 信息，
 * 就更新 Solr 数据库（使用 HTTP 请求）
 */
@SpringBootApplication
public class ReceiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveApplication.class, args);
    }
}
