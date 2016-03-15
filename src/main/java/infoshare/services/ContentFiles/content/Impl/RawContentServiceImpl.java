package infoshare.services.ContentFiles.content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.content.RawContent;
import infoshare.restapi.ContentFiles.content.RawContentAPI;
import infoshare.services.ContentFiles.content.RawContentService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by user9 on 2016/02/12.
 */
@Service
@SpringComponent
public class RawContentServiceImpl implements RawContentService {

    private static RawContentServiceImpl rawContentService=null;

    private RawContentServiceImpl(){}

    public  static RawContentServiceImpl getInstance(){
        if(rawContentService==null) {
            return new RawContentServiceImpl();
        }
        return rawContentService;
    }
    @Override
    public RawContent save(RawContent entity) {
        return RawContentAPI.save(entity);
    }

    @Override
    public RawContent update(RawContent entity) {
        return RawContentAPI.update(entity);
    }

    @Override
    public RawContent findById(String org, String id) {
        return RawContentAPI.findById(org,id);
    }

    @Override
    public void delete(RawContent entity) {
        RawContentAPI.save(entity);
    }

    @Override
    public Set<RawContent> findAll(String org) {
        return RawContentAPI.findAll(org);
    }

}
