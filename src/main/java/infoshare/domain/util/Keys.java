package infoshare.domain.util;

import java.io.Serializable;

/**
 * Created by codex on 2015/07/03.
 */
public class Keys implements Serializable, Comparable<Keys> {

    private String id;
    private String value;

    private Keys(){}
    public Keys(Builder builder) {
        this.id= builder.id;
        this.value = builder.value;
    }
    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }



    public static class Builder{
        private String id;
        private String value;

        public Builder(String value){
            this.value = value;
        }
        public Builder id(String id){
            this.id = id;
            return  this;
        }

        public Keys build(){
            return new Keys(this);
        }
    }
    @Override
    public int compareTo(Keys keys) {
        return id.compareTo(keys.id);
    }
}
