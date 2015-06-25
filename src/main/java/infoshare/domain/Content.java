package infoshare.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.nio.Buffer;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user13 on 2015/06/24.
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

    private Content(){

    }

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

    @Override
    public int compareTo(Content content) {
        return 0;
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

        public Builder(String creator){
            this.creator = creator;
        }

        public Builder content(Content tips){
            this.id = tips.id;
            this.dateCreated = tips.getDateCreated();
            this.creator = tips.getCreator();
            this.source = tips.getSource();
            this.category = tips.getCategory();
            this.title = tips.getTitle();
            this.content = tips.getContent();
            this.contentType = tips.getContentType();
            return this;
        }

        public Builder id(String value){
            this.id = value;
            return this;
        }
        public Builder dateCreated(Date value){
            this.dateCreated = value;
            return this;
        }
        public Builder creator(String value){
            this.creator = value;
            return this;
        }
        public Builder source(String value){
            this.source = value;
            return this;
        }
        public Builder category(String value){
            this.source = value;
            return this;
        }
        public Builder title(String value){
            this.title = value;
            return this;
        }
        public Builder content(String value){
            this.content = value;
            return this;
        }
        public Builder contentType(String value){
            this.contentType = value;
            return this;
        }

        public Content build(){
            return new Content(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            return !(id != null ? !id.equals(builder.id) : builder.id != null);

        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }


    }



    public String getId(){
        return id;
    }
    public Date getDateCreated(){
        return dateCreated;
    }
    public String getCreator(){
        return creator;
    }
    public String getSource(){
        return source;
    }
    public String getCategory(){
        return category;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getContentType(){
        return contentType;
    }
}
