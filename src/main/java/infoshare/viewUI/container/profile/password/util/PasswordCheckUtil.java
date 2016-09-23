package infoshare.viewUI.container.profile.password.util;


import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.PasswordHash;
import infoshare.domain.person.Person;

import java.io.Serializable;

/**
 * Created by hashcode on 2015/12/07.
 */
public class PasswordCheckUtil implements Serializable {


    private final Person user = GetUserCredentials.getUser();

    public boolean checkOldPassowrd(String password) {
        boolean isCorrect = false;
        String savedPassword = user.getAuthvalue();
        if (PasswordHash.check(password, savedPassword)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public static boolean comparePasswords(String password, String repeat) {
        boolean isSame = false;
        if (password.equals(repeat)) {
            isSame = true;
        }
        return isSame;
    }
}
