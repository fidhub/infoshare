package infoshare.service;

import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by user9 on 2015/07/20.
 */
public class categoryTest extends TestCase{

    private CategoryService categoryService = new CategoryServiceImpl();


    @Test
    public void testReadAll() throws Exception {
        List<Category> cat = categoryService.findAll();
        System.out.print(cat.get(0).getId());
        Assert.assertTrue(!cat.isEmpty());
        //Assert.assertEquals(cat.get(0).getName(),"Pregnancy");
    }

    @Test
    public void testfindOne() throws Exception {
        Category catfind = categoryService.find("2");
        Assert.assertEquals(catfind.getName(),"Emergency");
    }

    @Test
    public void testUpdate() throws Exception {
        Category category = categoryService.find("cdda6c6fae79c7cd970b4ec297bc0bf1");
        Category categoryUpdate = new Category.Builder(getName())
                                    .copy(category).description("Feeding a child human breast milk. According to the" +
                          " American Academy of Pediatrics, human breast milk is preferred for all infants. ").build();
        categoryService.merge(categoryUpdate);
        Category checkUpdated = categoryService.find("cdda6c6fae79c7cd970b4ec297bc0bf1");
        Assert.assertEquals(category.getDescription(), checkUpdated.getDescription());
    }
}
