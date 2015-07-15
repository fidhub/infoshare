package infoshare.services.Content.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by codex on 2015/06/24.
 */
@Service
@SpringComponent
public class ContentServiceImp implements ContentService {

    static Map<String,Content> contents = null;

    public ContentServiceImp() {
    if(contents == null) {
        contents = new HashMap<>();
        Content content1 = new Content.Builder("HIV").category("Pregnancy")
                .contentType("raw").creator("thule")
                .source("jozi")
                .content("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt" +
                        " ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud" +
                        " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                        " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
                        "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa" +
                        " qui officia deserunt mollit anim id est laborum.")
                .dateCreated(new Date()).id("5").build();

        Content content2 = new Content.Builder("TB").category("Pharmacy")
                .contentType("published").creator("thule")
                .source("jozi")
                .content("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .dateCreated(new Date()).id("6").build();

        Content content3 = new Content.Builder("Cancer").category("Circumcision")
                .contentType("edited").creator("thule")
                .source("jozi")
                .content("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .dateCreated(new Date()).id("7").build();

        contents.put(content1.getId(),content1);
        contents.put(content2.getId(),content2);
        contents.put(content3.getId(),content3);
    }
    }
    @Override
    public Content find(String s) {
        return contents.get(s);
    }

    @Override
    public Content save(Content entity) {
        contents.put(entity.getId(),entity);
        return contents.get(entity.getId());
    }

    @Override
    public Content merge(Content entity) {
        contents.put(entity.getId(),entity);
        return contents.get(entity.getId());
    }

    @Override
    public void remove(Content entity) {
            contents.remove(entity.getId());
    }

    @Override
    public List<Content> findAll() {
        return new ArrayList<>(contents.values());
    }
}
