package infoshare.service;


import infoshare.domain.content.ContentType;
import infoshare.services.ContentFiles.ContentType.ContentTypeService;
import infoshare.services.ContentFiles.ContentType.Impl.ContentTypeServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Songezo on 2015-09-30.
 */
public class ContentTypeTest {

    ContentTypeService contentTypeService = new ContentTypeServiceImpl();
    @Test
    public void testReadAll() throws Exception {
        Set<ContentType> contentType = contentTypeService.findAll();
        //getting the ID
        Assert.assertTrue(!contentType.isEmpty());
        }
    @Test
    public void testUpdate() throws Exception {
        //finding the old ContentType ID
        ContentType contentType = contentTypeService.findById("7330628ddb3380d7ad0b24465a78d81f");

        //Updating the ContentFiles Type
        ContentType contentTypeUpdate = new ContentType.Builder()
                .copy(contentType).description("edited copy  of the raw content").build();

        //Merging the updated content Type to the old one
        contentTypeService.update(contentTypeUpdate);
        ContentType checkUpadate = contentTypeService.findById("7330628ddb3380d7ad0b24465a78d81f");

        //Testing if the new ContentFiles Type is the same as the Old one
        Assert.assertEquals(contentType.getDescription(), checkUpadate.getDescription());
        }
}
