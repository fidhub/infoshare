package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.content.models.ContentModel;
import infoshare.domain.Content;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by user9 on 2015/07/24.
 */
public class ContentTest {
    @Test
    public void testPOST() throws Exception {
        ContentModel model = new ContentModel();
        model.setDateCreated(new Date());
        model.setCreator("songezo mkumathela");
        model.setSource("source name");
        model.setCategory("treatment");
        model.setTitle("hiv treatment guidelines");
        model.setContent("Two");
        model.setContentType("raw");

        RestApiConnectorClass.create(UrlPath.ContentLinks.POST, model, ContentModel.class);
    }
    @Test
    public void testPUT() throws Exception {
        Content content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "d592b8cd7f48b0eee1e1f8e8f5988ab5",
                Content.class);
        Content content1 = new Content.Builder(content.getTitle()).copy(content)
                .source("9d57cca65eaf056735157f119f2a467b")
                .category("3699255c536bcff9348f0de806866847")
                .contentType("edited").build();
        RestApiConnectorClass.update(UrlPath.ContentLinks.PUT,content1);
    }

    @Test
    public  void testGet() throws Exception {
        Content content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "1054c0771746780c91b6a2217f6236b1",
                Content.class);
        System.out.println(content.getId());

    }

    @Test
    public void testRaw() throws Exception {
        List<Boolean> list = RestApiConnectorClass.readAll(UrlPath.ContentLinks .isEditedAndPlublished+"98fe2ef595181b72cdd3ec085508fb40",Boolean.class);

        List<Content> contents = RestApiConnectorClass.readAll(UrlPath.ContentLinks.GETALL,Content.class);
        System.out.println(list);
        for(int i=0; i<list.size(); i++) {
            if(list.get(i)== true)
              System.out.println(i+"\t Edited\t"+contents.get(i).getId()+"\t"+contents.get(i).getSource());
            if(list.get(i) == false){
                System.out.println(i+"\t raw\t"+contents.get(i).getId()+"\t"+contents.get(i).getSource());
            }
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<Content> contents = RestApiConnectorClass.readAll(UrlPath.ContentLinks.GETALL,Content.class);
        System.out.println(contents);
        Assert.assertFalse(contents.isEmpty());
    }
}
