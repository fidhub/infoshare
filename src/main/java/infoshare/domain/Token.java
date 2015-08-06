package infoshare.domain;

import java.io.Serializable;

/**
 * Created by codex on 2015/07/03.
 */
public class Token implements Serializable, Comparable<Token> {
    private String id;
    private String token;

    private Token(Builder builder){
        this.id= builder.id;
        this.token = builder.token;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int compareTo(Token token) {
        return id.compareTo(token.id);
    }

    public static class Builder{
        private String id;
        private String token;

        public Builder(String token){
            this.token = token;
        }
        public Builder id(String id){
            this.id = id;
            return  this;
        }
        public Token build(){
            return new Token(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return !(id != null ? !id.equals(token.id) : token.id != null);

    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
