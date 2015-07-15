package infoshare.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by codex on 2015/06/24.
 */
public class Content implements Serializable, Comparable<Content> {

    private String id;
    private Date dateCreated;
    private String creator;
    private String source;
    private String category;
    private String title;
    private String content;
    private String contentType;

    private Content(Builder builder){

        this.id = builder.id;
        this.dateCreated = builder.dateCreated;
        this.creator = builder.creator;
        this.source = builder.source;
        this.category = builder.category;
        this.title = builder.title;
        this.content = builder.content;
        this.contentType = builder.contentType;
    }

    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getCreator() {
        return creator;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public String getContentType() {
        return contentType;
    }

    public static class Builder{
        private String id;
        private Date dateCreated;
        private String creator;
        private String source;
        private String category;
        private String title;
        private String content;
        private String contentType;

        public Builder(String title){
            this.title = title;
        }
        public Builder id(String id){
            this.id =id;
            return this;
        }
        public Builder dateCreated(Date dateCreated){
            this.dateCreated = dateCreated;
            return this;
        }
        public Builder creator(String creator){
            this.creator = creator;
            return  this;
        }
        public Builder source(String source){
            this.source = source;
            return this;
        }
        public Builder category(String category){
            this.category = category;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder contentType(String contentType){
            this.contentType = contentType;
            return this;
        }

        public Content build(){
            return new Content(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        return !(id != null ? !id.equals(content.id) : content.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Content content) {
        return title.compareTo(content.title);
    }
}
