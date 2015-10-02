package infoshare.service;

import infoshare.domain.Source;
import infoshare.services.source.SourceService;
import infoshare.services.source.sourceServiceImpl.SourceServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Songezo on 2015-10-02.
 */
public class SourceTest extends TestCase {
    private SourceService sourceService = new SourceServiceImpl();

    @Test
    public void testReadAll() throws Exception {
        List<Source> sources = sourceService.findAll();
        System.out.print(sources.get(0).getId());
        Assert.assertTrue(!sources.isEmpty());
    }

    @Test
    public void testFindOne() throws Exception {
        Source srcFind = sourceService.find("1");
        Assert.assertEquals(srcFind.getName(), "Mobile Phone");
    }

    @Test
    public void testUpdate() throws Exception {
        Source source = sourceService.find("0fa5487777fc3a71cb77608a58926569");
        Source sourceUpdate = new Source.Builder(getName()).copy(source).description("Emergency patients").build();

        sourceService.merge(sourceUpdate);
        Source sourceUpdated = sourceService.find("0fa5487777fc3a71cb77608a58926569");
        Assert.assertEquals(source.getDescription(), sourceUpdated.getDescription());
    }
}
