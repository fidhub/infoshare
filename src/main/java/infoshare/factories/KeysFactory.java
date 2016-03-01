package infoshare.factories;

import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Keys;

import java.util.Map;

/**
 * Created by codet on 2016/02/23.
 */
public class KeysFactory {

    public static Keys getKeys(Map<String,String> keyVals){

        Keys keys = new Keys.Builder(keyVals.get("value"))
                .id(KeyGenerator.getEntityId())
                .build();
        return keys;
    }
}