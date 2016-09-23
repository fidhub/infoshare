package infoshare.service;

import infoshare.app.facade.SourceFacade;
import infoshare.services.ContentFiles.source.SourceService;
import junit.framework.TestCase;

/**
 * Created by Songezo on 2015-10-02.
 */
public class SourceTest extends TestCase {
    private SourceService sourceService = SourceFacade.sourceService;

    /*@Test
    public void testReadAll() throws Exception {
        Set<Source> sources = sourceService.findAll();
        Assert.assertTrue(!sources.isEmpty());
    }

    @Test
    public void testFindOne() throws Exception {
        Source srcFind = sourceService.findById("1");
        Assert.assertEquals(srcFind.getName(), "Mobile Phone");
    }

    @Test
    public void testUpdate() throws Exception {
        Source source = sourceService.findById("0fa5487777fc3a71cb77608a58926569");
        Source sourceUpdate = new Source.Builder(getName()).copy(source).description("Emergency patients").build();

        sourceService.update(sourceUpdate);
        Source sourceUpdated = sourceService.findById("0fa5487777fc3a71cb77608a58926569");
        Assert.assertEquals(source.getDescription(), sourceUpdated.getDescription());
    }*/
}
