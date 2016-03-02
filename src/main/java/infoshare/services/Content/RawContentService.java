package infoshare.services.Content;

import infoshare.domain.content.RawContent;
import infoshare.services.Services;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
public interface RawContentService {
    RawContent save( RawContent entity);
    RawContent update( RawContent entity);
    RawContent findById( String org,String id);
    void delete( RawContent entity);
    Set<RawContent> findAll(String org);
}
