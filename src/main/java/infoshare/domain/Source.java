package infoshare.domain;

import java.io.Serializable;

/**
 * Created by codex on 2015/06/27.
 */
public class Source implements Serializable, Comparable<Source> {

    private String id;
    private String name;
    private String description;

    private Source() {
    }

    public Source(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder{
        private String id;
        private String name;
        private String description;

        public Builder(String name){
            this.name = name;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Source build(){
            return new Source(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Source source = (Source) o;

        return !(id != null ? !id.equals(source.id) : source.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(Source source) {
        return name.compareTo(source.name);
    }
}

