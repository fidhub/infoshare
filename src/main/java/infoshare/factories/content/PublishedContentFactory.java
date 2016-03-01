package infoshare.factories.content;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.content.PublishedContent;

import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/03/01.
 */
public class PublishedContentFactory {

        public static PublishedContent getPublishedContent(Map<String,String> publishedContentVals,Date date) {
                PublishedContent publishedContent = new PublishedContent.Builder()
                        .id(KeyGenerator.getEntityId())
                        .dateCreated(date)
                        .source(publishedContentVals.get("source"))
                        .category(publishedContentVals.get("category"))
                        .title(publishedContentVals.get("title"))
                        .content(publishedContentVals.get("content"))
                        .contentType(publishedContentVals.get("contentType"))
                        .status(publishedContentVals.get("status"))
                        .state(publishedContentVals.get("state"))
                        .org(publishedContentVals.get("org"))
                        .build();
                return publishedContent;
        }
}
