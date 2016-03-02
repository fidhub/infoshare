package infoshare.domain.util;

import java.io.Serializable;

/**
 * Created by codex on 2015/07/03.
 */
public class Token implements Serializable, Comparable<Token> {

    private String id;
    private String tokenValue;
    private Token(){}

    public Token(Builder builder){
        this.id= builder.id;
        this.tokenValue = builder.tokenValue;
    }
    public String getId() {
        return id;
    }
    public String getTokenValue() {
        return tokenValue;
    }

    public static class Builder{
        private String id;
        private String tokenValue;

        public Builder tokenValue(String tokenValue){
            this.tokenValue = tokenValue;
            return this;
        }
        public Builder id(String id){
            this.id = id;
            return  this;
        }
        public Builder copy(Token token){
            this.id = token.id;
            this.tokenValue = token.tokenValue;

            return this;
        }
        public Token build(){
            return new Token(this);
        }
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Token token) {
        return id.compareTo(token.id);
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
