package infoshare.domain.storage;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/01/04.
 */
public class FileResults implements Serializable, Comparable<FileResults> {
    private String id;
    private String url;
    private String size;
    private String mime;

    private FileResults() {
    }

    public FileResults(Builder builder) {
        this.id = builder.id;
        this.url = builder.url;
        this.size = builder.size;
        this.mime = builder.mime;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

    public String getMime() {
        return mime;
    }

    public static class Builder{
        private String id;
        private String url;
        private String size;
        private String mime;


        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder mime(String mime) {
            this.mime = mime;
            return this;
        }

        public Builder copy(FileResults fileResults){
            this.id = fileResults.id;
            this.url = fileResults.url;
            this.size = fileResults.size;
            this.mime = fileResults.mime;

            return  this;
        }

        public FileResults build(){return  new FileResults(this);}
    }

    public static Builder builder(){return  new Builder();}
    @Override
    public int compareTo(FileResults o) {
        return  url.compareTo(o.url);
    }
}
