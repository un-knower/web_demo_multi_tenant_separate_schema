package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.es.EsClient;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 16/11/28.
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Resource
    private EsClient esClient;

    @Override
    public IndexResponse createDocument(String index, String type, String json) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(json), "json is null");

        IndexResponse response = esClient.getTransportClient().prepareIndex(index, type)
                .setSource(json)       //must
                .get();

        return response;
    }

    @Override
    public GetResponse getDocument(String index, String type, String id) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        GetResponse response = esClient.getTransportClient().prepareGet(index, type, id).get();
        return response;
    }

    @Override
    public DeleteResponse deleteDocument(String index, String type, String id) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        DeleteResponse response = esClient.getTransportClient().prepareDelete(index, type, id).get();
        return response;
    }

    @Override
    public UpdateResponse updateDocumentByDoc(String index, String type, String id, Map<String, Object> data) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        //method 1
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index(index);
//        updateRequest.type(type);
//        updateRequest.id(id);
//
//        updateRequest.doc(data);
//        UpdateResponse response = null;
//        try {
//            response = esClient.getTransportClient().update(updateRequest).get();
//        } catch (InterruptedException e) {
//            logger.error("update document error", e);
//        } catch (ExecutionException e) {
//            logger.error("update document error", e);
//        }

        //method 2
        UpdateResponse response = esClient.getTransportClient().prepareUpdate(index, type, id)
                .setDoc(data)
                .get();

        return response;
    }

    @Override
    public UpdateResponse updateDocumentByScript(String index, String type, String id, Script script) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        UpdateResponse response = esClient.getTransportClient().prepareUpdate()
                .setScript(script)
                .get();
        return response;
    }

    @Override
    public UpdateResponse upsertDocumet(String index, String type, String id, String document, Map<String, Object> data) {
        Preconditions.checkState(StringUtils.isNotBlank(index), "index is null!");
        Preconditions.checkState(StringUtils.isNotBlank(type), "type is null");
        Preconditions.checkState(StringUtils.isNotBlank(id), "id is null");

        IndexRequest indexRequest = new IndexRequest(index, type, id)
                .source(document);

        UpdateRequest updateRequest = new UpdateRequest(index, type, id)
                .doc(data)
                .upsert(indexRequest);

        UpdateResponse response = null;
        try {
            response = esClient.getTransportClient().update(updateRequest).get();
        } catch (InterruptedException e) {
            logger.error("upsert document error", e);
        } catch (ExecutionException e) {
            logger.error("upsert document error", e);
        }
        return response;
    }
}
