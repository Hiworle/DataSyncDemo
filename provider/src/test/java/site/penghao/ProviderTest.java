package site.penghao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import site.penghao.bean.Product;
import site.penghao.dubbo.service.ProductDubboService;
import site.penghao.mapper.ProductMapper;

@SpringBootTest(classes = ProviderApplication.class)
public class ProviderTest {
    @Autowired
    ProductDubboService service;

    @Test
    public void test() {
        Product product = new Product(null, "VIVO", 5888.0);
        service.insertProduct(product);
    }
}
