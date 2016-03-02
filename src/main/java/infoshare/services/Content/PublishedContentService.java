package infoshare.services.Content;

import infoshare.domain.content.PublishedContent;
import infoshare.services.Services;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
public interface PublishedContentService  {

    PublishedContent save( PublishedContent entity);
    PublishedContent update( PublishedContent entity);
    PublishedContent findById( String org,String id);
    void delete( PublishedContent entity);
    Set<PublishedContent> findAll(String org);

}
