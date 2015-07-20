package infoshare;

import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;

/**
 * Created by user9 on 2015/07/20.
 */
public class categoryTest extends TestCase{

    private CategoryService categoryService = new CategoryServiceImpl();


    @Test
    public void testRead() throws Exception {
        List<Category> cat = categoryService.findAll();
        Assert.assertEquals(cat.get(0).getName(),"Pregnancy");
    }

    @Test
    public void testfindOne() throws Exception {
        Category catfind = categoryService.find("2");
        Assert.assertEquals(catfind.getName(),"Emergency");
    }
}
