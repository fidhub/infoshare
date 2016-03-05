package infoshare.client.content.profile.demographics.model;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/20.
 */
public class PersonIdentitiesModel implements Serializable {
    private String idType;
    private String idValue;
    private boolean preffered;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public boolean isPreffered() {
        return preffered;
    }

    public void setPreffered(boolean preffered) {
        this.preffered = preffered;
    }
}
