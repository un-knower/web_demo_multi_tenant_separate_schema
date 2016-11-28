package com.sky.demo.web_demo_multi_tenant_separate_schema.es.index;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.EsClient;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by user on 16/11/28.
 */
@Service
public class IndexServiceImpl implements IndexService {

    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Resource
    private EsClient esClient;

    @Override
    public IndexResponse createIndexWithDocument(String index, String type, String json) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(json), "type is null");

        IndexResponse response = esClient.getTransportClient().prepareIndex(index, type)
                .setSource(json)       //must
                .get();

        return response;
    }
}
