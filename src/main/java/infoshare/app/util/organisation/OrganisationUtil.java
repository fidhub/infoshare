package infoshare.app.util.organisation;

import infoshare.app.util.security.GetUserCredentials;

/**
 * Created by hashcode on 2016/03/05.
 */
public class OrganisationUtil {
    public static final String getCompanyCode() {
        return GetUserCredentials.getUser().getOrg();
    }
    public static final String getPersonID() {
        return GetUserCredentials.getUser().getId();
    }
}


