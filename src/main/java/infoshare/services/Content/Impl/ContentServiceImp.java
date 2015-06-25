package infoshare.services.Content.Impl;

import infoshare.domain.Content;
import infoshare.services.Content.ContentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by codex on 2015/06/24.
 */
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

        Content content1 = new Content.Builder("cput").content("qwertyuioijhgfdsasdfghjkjhyt")
                .contentType("Raw").creator("thuleboba").title("Hiv")
                .dateCreated(new Date()).Description("hkkkhkhkhkkhkh").build();
        contents.add(content1);
        return contents;
    }
}
