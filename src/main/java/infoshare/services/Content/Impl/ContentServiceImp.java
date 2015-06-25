package infoshare.services.Content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by codex on 2015/06/24.
 */
@Service
@SpringComponent
public class ContentServiceImp implements ContentService {
    @Override
    public Content find(String s) {
        return null;
    }

    @Override
    public Content save(Content entity) {
        return null;
    }

    @Override
    public Content merge(Content entity) {
        return null;
    }

    @Override
    public void remove(Content entity) {

    }

    @Override
    public List<Content> findAll() {
        List<Content> contents = new ArrayList<>();

        Content content1 = new Content.Builder("HIV").category("treatment")
                                .contentType("Raw").creator("thule")
                                .source("jozi").content("fffffffffffffffffffffffffffff")
                                .dateCreated(new Date()).build();

        Content content2 = new Content.Builder("TB").category("treatment")
                .contentType("edited").creator("thule")
                .source("jozi").content("fffffffffffffffffffffffffffff")
                .dateCreated(new Date()).build();

        Content content3 = new Content.Builder("CANCER").category("treatment")
                .contentType("published").creator("thule")
                .source("jozi").content("fffffffffffffffffffffffffffff")
                .dateCreated(new Date()).build();

        contents.add(content1);
        contents.add(content2);
        contents.add(content3);
        return contents;
    }
}
