package site.penghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.penghao.bean.SearchPojo;
import site.penghao.service.SearchService;

@Controller
public class SearchController {

    @Autowired
    private SearchService service;


    @RequestMapping("/insert")
    @ResponseBody
    public boolean insert(SearchPojo pojo) {
        return service.insert(pojo);
    }
}
