package infoshare.services.ContentFiles.content;

import infoshare.domain.content.PublishedContent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Component
public interface PublishedContentService  {

    PublishedContent save( PublishedContent entity);
    PublishedContent update( PublishedContent entity);
    PublishedContent findById( String org,String id);
    void delete( PublishedContent entity);
    Set<PublishedContent> findAll(String org);

}
