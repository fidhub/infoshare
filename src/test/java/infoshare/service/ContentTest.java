package infoshare.service;

import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import infoshare.services.Content.Impl.ContentServiceImp;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Songezo on 2015-09-29.
 */
public class ContentTest extends TestCase {
    
    private ContentService contentService = new ContentServiceImp();

    @Test
    public void testRead() throws Exception {
        List<Content> conts = contentService.findAll();
        Assert.assertEquals(conts.get(0).getTitle(), "HIV prevention");
    }

    @Test
    public void testGetAll() throws Exception {
        Content content = contentService.find("bb31d3c323f6e891cc62aec8afe7596e");
        Assert.assertEquals(content.getCreator(), "Songezo Kamila");
    }


}
