package infoshare.service;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
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
    public void testRaw() throws Exception {
        List<Boolean> list = RestApiConnectorClass.readAll(UrlPath.ContentLinks.isEdited + "98fe2ef595181b72cdd3ec085508fb40", Boolean.class);

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

}
