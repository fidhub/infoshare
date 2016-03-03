package infoshare.services.ContentFiles.content;

import infoshare.domain.content.EditedContent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Component
public interface EditedContentService {
    EditedContent save( EditedContent entity);
    EditedContent update( EditedContent entity);
    EditedContent findById( String org,String id);
    void delete( EditedContent entity);
    Set<EditedContent> findAll(String org);
}
