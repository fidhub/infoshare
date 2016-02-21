package infoshare.domain;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/01/05.
 */
public class StorageUrl implements Serializable {
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public StorageUrl(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
    }

    public static class Builder {
        private String id;
        private String name;
        private String url;

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder url(String value) {
            this.url = value;
            return this;
        }

        public Builder copy(StorageUrl value) {
            this.id = value.id;
            this.name = value.name;
            this.url = value.url;
            return this;
        }

        public StorageUrl build() {
            return new StorageUrl(this);

        }
    }
}
