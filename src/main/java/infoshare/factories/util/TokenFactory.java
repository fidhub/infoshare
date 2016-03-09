package infoshare.factories.util;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Token;

/**
 * Created by user9 on 2016/03/02.
 */
public class TokenFactory {

    public static Token getToken(String tokenVal){
        Token token = new Token.Builder()
                .id(KeyGenerator.getEntityId())
                .tokenValue(tokenVal)
                .build();
        return token;
    }
}
