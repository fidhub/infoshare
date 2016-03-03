package infoshare.client.content.setup.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Songezo on 2016-02-12.
 */
public class UserDetailsModel implements Serializable {

    private String id;
    @NotNull
    private String UserName;
    @NotNull
    private String FirstName;
    @NotNull
    private String LastName;

    public String getId() {
        return id;
    }

    public String getUserName(){
        return UserName;
    }

    public String getFirstName(){
        return FirstName;
    }

    public String getLastName(){
        return LastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String Username){
        this.UserName = Username;
    }

    public void setFirstName(String FirstNmae){
        this.FirstName = FirstNmae;
    }

    public void setLastName(String LadtName){
        this.LastName = LadtName;
    }


}
