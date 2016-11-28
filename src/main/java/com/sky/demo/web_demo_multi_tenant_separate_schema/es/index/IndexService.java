package com.sky.demo.web_demo_multi_tenant_separate_schema.es.index;

import org.elasticsearch.action.index.IndexResponse;

/**
 * Created by user on 16/11/28.
 */
public interface IndexService {


    public IndexResponse createIndexWithDocument(String index, String type, String json);



}
