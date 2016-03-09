package infoshare.restApi;

import infoshare.client.content.content.models.ContentModel;
import org.junit.Test;

import java.util.Date;

/**
 * Created by user9 on 2015/07/24.
 */
public class ContentTest {
    @Test
    public void testPOST() throws Exception {
        ContentModel model = new ContentModel();
        model.setDateCreated(new Date());
        model.setCreator("Songezo Mkumatela");
        model.setSource("mobile");
        model.setCategory("uncategorized");
        model.setTitle("HIV prevention");
        model.setContent(" Using any type of tobacco puts you on a collision course with cancer." +
                " Smoking has been linked to various types of cancer — including cancer of the lung, bladder," +
                " cervix and kidney. And chewing tobacco has been linked to cancer of the oral cavity and pancreas." +
                " Even if you don't use tobacco, exposure to secondhand smoke might increase your risk of lung cancer." +
                "Avoiding tobacco — or deciding to stop using it — is one of the most important health decisions you can make." +
                " It's also an important part of cancer prevention. If you need help quitting tobacco," +
                " ask your doctor about stop-smoking products and other strategies for quitting.");
        model.setState("active");
        model.setContentType("Text");
        model.setStatus("raw");

      //  RestUtil.save(ContentBaseUrl.Raw.POST, model, ContentModel.class);
    }
  /*  @Test
    public void testPUT() throws Exception {
        ContentFiles content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "d592b8cd7f48b0eee1e1f8e8f5988ab5",
                ContentFiles.class);
        ContentFiles content1 = new ContentFiles.Builder(content.getTitle()).copy(content)
                .source("9d57cca65eaf056735157f119f2a467b")
                .category("3699255c536bcff9348f0de806866847")
                .contentType("edited").build();
        RestApiConnectorClass.update(UrlPath.ContentLinks.PUT, content1);
    }

    @Test
    public  void testGet() throws Exception {
        ContentFiles content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "1054c0771746780c91b6a2217f6236b1",
                ContentFiles.class);
        System.out.println(content.getId());

    }

    @Test
    public void testGetAll() throws Exception {
        List<ContentFiles> contents = RestApiConnectorClass.readAll(UrlPath.ContentLinks.GETALL,ContentFiles.class);
        System.out.println(contents);
        Assert.assertFalse(contents.isEmpty());
    }

    @Test
    public void testUpdateAll() throws Exception {
        EditedContentService contentService = new EditedContentServiceImpl();
        for(EditedContent content:contentService.findAll().stream()
                .filter(cont -> cont.getState().equalsIgnoreCase("active"))
                .collect(Collectors.toList()).stream()
                .filter(cont -> cont.getStatus().equalsIgnoreCase("Edited"))
                .collect(Collectors.toList())){
            EditedContent content1 = new EditedContent.Builder(content.getTitle()).copy(content)
                    .category("uncategorized").build();
            RestApiConnectorClass.update(UrlPath.EditedLinks.PUT, content1);
        }
    }*/
}
