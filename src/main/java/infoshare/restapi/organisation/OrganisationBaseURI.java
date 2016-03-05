package infoshare.restapi.organisation;


import infoshare.app.conf.RestUtil;

/**
 * Created by hashcode on 2015/11/15.
 */
public class OrganisationBaseURI {

    public static class Organisation {
        public static final String POST = RestUtil.URL + "/api/organisation/post";
        public static final String GET_ID = RestUtil.URL + "/api/organisation/get/";
        public static final String GETALL = RestUtil.URL + "/api/organisations/get";

    }

    public static class OrganisationLogo {
        public static final String POST = RestUtil.URL + "/api/organisationlogo/post";
        public static final String GET_ID = RestUtil.URL + "/api/organisationlogo/get/";
        public static final String GETALL = RestUtil.URL + "/api/organisationlogos/get/";

    }
}
