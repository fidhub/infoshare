package infoshare.factories.util;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Keys;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class KeysFactory {

    public static Keys getKeys(String keyVal){

        Keys keys = new Keys.Builder()
                .id(KeyGenerator.getEntityId())
                .value(keyVal)
                .build();
        return keys;
    }
}