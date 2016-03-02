package infoshare.domain.demographics;

import java.io.Serializable;

/**
 * Created by user9 on 2016/03/02.
 */
public class Language implements Serializable,Comparable<Language> {

    private String id;
    private String name;
    private String state;

    private Language(){}
    public Language(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.state = builder.state;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
    public static class Builder{
        private String id;
        private String name;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }
        public Builder copy(Language language){
            this.id = language.id;
            this.name = language.name;
            this.state = language.state;

            return this;
        }
        public Language build(){return new Language(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Language language) {
        return name.compareTo(language.name);
    }
}
