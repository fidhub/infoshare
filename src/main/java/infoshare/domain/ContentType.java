package infoshare.domain;

import java.io.Serializable;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentType implements Serializable, Comparable<ContentType> {

    private String id;
    private String contentTyeName;
    private String contentTyeDescription;

    private ContentType(Builder builder) {
        this.id = builder.id;
        this.contentTyeName = builder.contentTyeName;
        this.contentTyeDescription = builder.contentTyeDescription;
    }

    public String getId() {
        return id;
    }

    public String getContentTyeName() {
        return contentTyeName;
    }

    public String getContentTyeDescription() {
        return contentTyeDescription;
    }
    @Override
    public int compareTo(ContentType contentType) {
        return contentTyeName.compareTo(contentType.contentTyeName);
    }

    public static class Builder{
        private String id;
        private String contentTyeName;
        private String contentTyeDescription;

        public Builder(String contentName){
            this.contentTyeName = contentName;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder contentTyeDescription(String description){
            this.contentTyeDescription = description;
            return this;
        }
        public ContentType build(){
            return  new ContentType(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentType that = (ContentType) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
