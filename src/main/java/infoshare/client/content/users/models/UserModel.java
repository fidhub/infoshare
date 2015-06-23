package infoshare.client.content.users.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hashcode on 2015/06/23.
 */
public class UserModel implements Serializable {

    private String id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String mainNumber;
    private String otherNumber;
    private String emailAddress;
    private String address;
    private String position;


}