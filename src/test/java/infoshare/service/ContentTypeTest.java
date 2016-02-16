package infoshare.service;


import infoshare.domain.ContentType;
import infoshare.services.ContentType.ContentTypeService;
import infoshare.services.ContentType.Impl.ContentTypeServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Songezo on 2015-09-30.
 */
public class ContentTypeTest {

    ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    @Test
    public void testReadAll() throws Exception {
        List<ContentType> contentType = contentTypeService.findAll();
        //getting the ID
        System.out.print(contentType.get(0).getId());
        Assert.assertTrue(!contentType.isEmpty());
        }
    @Test
    public void testUpdate() throws Exception {
        //finding the old ContentType ID
        ContentType contentType = contentTypeService.find("7330628ddb3380d7ad0b24465a78d81f");

        //Updating the Content Type
        ContentType contentTypeUpdate = new ContentType.Builder(contentType.getName())
                .copy(contentType).description("edited copy  of the raw content").build();

        //Merging the updated content Type to the old one
        contentTypeService.merge(contentTypeUpdate);
        ContentType checkUpadate = contentTypeService.find("7330628ddb3380d7ad0b24465a78d81f");

        //Testing if the new Content Type is the same as the Old one
        Assert.assertEquals(contentType.getDescription(), checkUpadate.getDescription());
        }
}
