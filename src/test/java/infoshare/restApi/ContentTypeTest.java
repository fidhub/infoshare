package infoshare.restApi;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.client.content.systemValues.models.ContentTypeModel;
import infoshare.domain.ContentType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by user9 on 2015/07/24.
 */
public class ContentTypeTest extends TestCase {

    //id 1 :83312a3616830b5ebba284e3635f2d13
    @Test
    public void testPut() throws Exception {

        ContentTypeModel model = new ContentTypeModel();
        model.setName("Raw");
        model.setDescription("raw content");
        RestApiConnectorClass.create(UrlPath.ContentTypeLinks.POST,model,ContentTypeModel.class);
    }
    @Test
    public void testPost() throws Exception {
        ContentType contentType = RestApiConnectorClass.read(UrlPath.ContentTypeLinks.GET_ID,
                "83312a3616830b5ebba284e3635f2d13",ContentType.class);
        ContentType contentType1 = new ContentType.Builder("Edited")
                    .description("edited content type").id(contentType.getId()).build();

        RestApiConnectorClass.update(UrlPath.ContentTypeLinks.PUT, contentType1);
        Assert.assertEquals(contentType.getName(), contentType1.getName());

    }

    @Test
    public  void testGet() throws Exception {
       ContentType contentType = RestApiConnectorClass.read(UrlPath.ContentTypeLinks.GET_ID,
                "83312a3616830b5ebba284e3635f2d13", ContentType.class);
        Assert.assertEquals(contentType.getName(),"Edited");
    }

    @Test
    public void testGetAll() throws Exception {
      List<ContentType> contentTypes = RestApiConnectorClass.readAll(UrlPath.ContentTypeLinks.GETALL, ContentType.class);
        Assert.assertTrue(contentTypes.isEmpty());
    }
}
