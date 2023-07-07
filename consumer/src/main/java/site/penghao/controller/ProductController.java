package site.penghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.penghao.bean.Product;
import site.penghao.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String showAdd() {
        return "add";
    }

    @PostMapping("/add")
    public String add(Product product) {
        int index = service.insertProduct(product);
        System.out.println(index);
        return "add";
    }
}
