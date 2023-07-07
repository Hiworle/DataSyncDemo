package site.penghao.dubbo.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.penghao.bean.Product;
import site.penghao.dubbo.service.ProductDubboService;
import site.penghao.mapper.ProductMapper;

@DubboService
public class ProductDubboServiceImpl implements ProductDubboService {

    @Autowired
    private ProductMapper mapper;

    @Override
    public int insertProduct(Product product) {
        return mapper.insertProduct(product);
    }
}
