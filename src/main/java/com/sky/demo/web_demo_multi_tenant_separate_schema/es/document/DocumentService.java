package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.script.Script;

import java.util.Map;

/**
 * Created by user on 16/11/28.
 */
public interface DocumentService {

    public IndexResponse createDocument(String index, String type, String json);

    public GetResponse getDocument(String index, String type, String id);

    public DeleteResponse deleteDocument(String index, String type, String id);

    public UpdateResponse updateDocumentByDoc(String index, String type, String id, Map<String, Object> data);

    public UpdateResponse updateDocumentByScript(String index, String type, String id, Script script);

    public UpdateResponse upsertDocumet(String index, String type, String id, String document, Map<String, Object> data);

}
