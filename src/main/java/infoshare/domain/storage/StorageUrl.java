package infoshare.domain.storage;

import java.io.Serializable;

/**
 * Created by joindomain on 2/29/2016.
 */
public class StorageUrl implements Serializable,Comparable<StorageUrl> {
   private  String id;
   private  String name;
   private  String url;

    private StorageUrl() {
    }

    public StorageUrl(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder{

        private  String id;
        private  String name;
        private  String url;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder copy(StorageUrl storageUrl){
            this.id = storageUrl.id;
            this.name = storageUrl.name;
            this.url = storageUrl.url;

            return this;
        }
        public StorageUrl build(){return new StorageUrl(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(StorageUrl o) {
        return url.compareTo(o.url);
    }
}
