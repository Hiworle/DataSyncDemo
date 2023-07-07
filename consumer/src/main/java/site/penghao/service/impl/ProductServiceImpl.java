package site.penghao.service.impl;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.penghao.bean.Product;
import site.penghao.bean.SearchPojo;
import site.penghao.component.Sender;
import site.penghao.dubbo.service.ProductDubboService;
import site.penghao.service.ProductService;
import site.penghao.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @DubboReference
    private ProductDubboService service;

    @Autowired
    private Sender sender;

    @Override
    public int insertProduct(Product product) {

        Random random = new Random();
        // 保存到数据库
        int resultDB = service.insertProduct(product);

        // useGeneratedKeys 不起作用，这里不查 id 了
        product.setId(random.nextInt(50000));

        if (resultDB == 1) {
//            // 保存成功，同步到 Solr 中
//            Map<String, String> params = new HashMap<>();
//            params.put("id", product.getId().toString());
//            params.put("name", product.getName());
//            params.put("price", product.getPrice().toString());

//            // 同步进行，保存到 Solr
//            String resultSolr = HttpUtil.doPost("http://localhost:8090/insert", params);
//            if ("false".equals(resultSolr)) {
//                // 删除掉保存的数据
//                resultDB = 0;
//            }

            // 异步进行，将参数发送给消息队列
            SearchPojo searchPojo = new SearchPojo();
            BeanUtils.copyProperties(product, searchPojo);
            sender.send(searchPojo);
        }
        return resultDB;
    }
}
