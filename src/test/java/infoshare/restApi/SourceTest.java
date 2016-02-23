package infoshare.restApi;

import infoshare.restapi.RestApiConnectorClass;
import infoshare.client.content.systemValues.models.SourceModel;
import infoshare.domain.Source;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by user9 on 2015/07/24.
 */
public class SourceTest {
    //ID1:fcc51af60b794e9a83bd0fcdaff661fd
    //ID1:f737d27fc2d4f303e318f8ac6ef95702
    @Test
    public void testPut() throws Exception {
        SourceModel model = new SourceModel();

        model.setName("bbb");
        model.setDescription("fdhfdff");
        SourceModel sourceModel = RestApiConnectorClass.create(UrlPath.SourceLinks.POST, model, SourceModel.class);
        System.out.println(
              sourceModel.getName()+"\n"+sourceModel.getDescription()
       );
    }
    @Test
    public void testPost() throws Exception {
        Source source = RestApiConnectorClass.read(UrlPath.SourceLinks.GET_ID,
                "fcc51af60b794e9a83bd0fcdaff661fd",Source.class);

        Source source1 = new Source.Builder(source.getName())
                .copy(source).description("home sweet home").build();

        RestApiConnectorClass.update(UrlPath.SourceLinks.PUT,source1);

        Assert.assertEquals(source.getDescription(),source1.getDescription());
    }

    @Test
    public  void testGet() throws Exception {

        Source source = RestApiConnectorClass.read(UrlPath.SourceLinks.GET_ID,
                "fcc51af60b794e9a83bd0fcdaff661fd",Source.class);
        Assert.assertEquals(source.getName(),"fin");
    }

    @Test
    public void testGetAll() throws Exception {
        List<Source> sources = RestApiConnectorClass.readAll(UrlPath.SourceLinks.GETALL,Source.class);
        Assert.assertFalse(sources.isEmpty());
    }
}
