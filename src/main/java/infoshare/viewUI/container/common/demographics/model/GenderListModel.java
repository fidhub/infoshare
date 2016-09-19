package infoshare.viewUI.container.common.demographics.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hashcode on 2015/08/18.
 */
public class GenderListModel implements Serializable {
    @NotNull
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }
}
