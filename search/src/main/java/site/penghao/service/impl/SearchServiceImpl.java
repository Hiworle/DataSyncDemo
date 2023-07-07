package site.penghao.service.impl;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;
import site.penghao.bean.SearchPojo;
import site.penghao.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public boolean insert(SearchPojo pojo) {

        UpdateResponse response = solrTemplate.saveBean("testcore", pojo);
        solrTemplate.commit("testcore");

        return response.getStatus() == 0;

    }
}
