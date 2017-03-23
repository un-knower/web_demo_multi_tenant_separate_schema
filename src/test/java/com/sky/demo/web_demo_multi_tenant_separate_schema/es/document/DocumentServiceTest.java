package com.sky.demo.web_demo_multi_tenant_separate_schema.es.document;

import com.google.common.collect.Maps;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by user on 16/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml"})
public class DocumentServiceTest {

    @Resource
    private DocumentService documentService;

    @Test
    public void test_createDocument() {
        String index = "test-index";
        String type = "animal";
        String document = "{\n" +
                "    \"name\": \"Deck the halls\",\n" +
                "    \"year\": 1885\n" +
                "}";

        IndexResponse indexResponse = documentService.createDocument(index, type, document);

        System.out.println(indexResponse);
    }

    @Test
    public void test_getDocument() {
        String index = "test-index";
        String type = "animal";
        String id = "AViqdNQRJz8I0zRWn2BQ";

        GetResponse response = documentService.getDocument(index, type, id);
        System.out.println(response);
    }

    @Test
    public void test_deleteDocument() {
        String index = "test-index";
        String type = "animal";
        String id = "AViqdNQRJz8I0zRWn2BQ";

        DeleteResponse response = documentService.deleteDocument(index, type, id);
        System.out.println(response);
    }

    @Test
    public void test_updateDocumentByDoc() {
        String index = "test-index";
        String type = "animal";
        String id = "AViqdNQRJz8I0zRWn2BQ";
        Map<String, Object> data = Maps.newHashMap();
        data.put("", "");

        UpdateResponse response = documentService.updateDocumentByDoc(index, type, id, data);
        System.out.println(response);
    }

    @Test
    public void test_updateDocumentByScript() {
        String index = "test-index";
        String type = "animal";
        String id = "AViqdNQRJz8I0zRWn2BQ";

        Script script = new Script(ScriptType.INLINE, "", "", null);

        UpdateResponse response = documentService.updateDocumentByScript(index, type, id, script);
        System.out.println(response);
    }
}
