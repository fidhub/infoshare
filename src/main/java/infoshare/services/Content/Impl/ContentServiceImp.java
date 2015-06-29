package infoshare.services.Content.Impl;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import org.springframework.stereotype.Service;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by codex on 2015/06/24.
 */
@Service
@SpringComponent
public class ContentServiceImp implements ContentService {
    List<Content> contents = new ArrayList<>();

    public void addValues(){

        Content content1 = new Content.Builder("HIV").category("treatment")
                .contentType("edited").creator("thule")
                .source("jozi")
                .content("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt" +
                        " ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud" +
                        " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                        " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
                        "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa" +
                        " qui officia deserunt mollit anim id est laborum.")
                .dateCreated(new Date()).id("5").build();

        Content content2 = new Content.Builder("TB").category("treatment")
                .contentType("raw").creator("thule")
                .source("jozi")
                .content("psum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
                        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
                        "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
                        "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
                        "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                .dateCreated(new Date()).id("6").build();

        Content content3 = new Content.Builder("CANCER").category("treatment")
                .contentType("published").creator("thule")
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

        contents.add(content1);
        contents.add(content2);
        contents.add(content3);
    }
    @Override
    public Content find(String s) {
        Content content = null;
        addValues();
        for(Content cont: contents )
            if (cont.getId().equalsIgnoreCase(s))
                content = cont;

        return content;
    }

    @Override
    public Content save(Content entity) {
        return new Content.Builder(entity.getTitle())
                   .contentType(entity.getContentType())
                   .category(entity.getCategory())
                   .content(entity.getContent()).build();
    }

    @Override
    public Content merge(Content entity) {
        Content content = new Content.Builder(entity.getTitle())
                .contentType(entity.getContentType())
                .category(entity.getCategory())
                .content(entity.getContent()).build();

        System.out.println(entity.getContent()+ "\n"+entity.getContentType());
                return content;
    }

    @Override
    public void remove(Content entity) {

    }

    @Override
    public List<Content> findAll() {
        addValues();
        return contents;
    }
}
