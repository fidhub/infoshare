package infoshare.restapi.people;


import infoshare.app.conf.RestUtil;

/**
 * Created by hashcode on 2015/11/17.
 */
public class PersonBaseURI {

    public static class Person {
        public static final String POST = RestUtil.URL + "/api/people/post";
        public static final String GET_ID = RestUtil.URL + "/api/people/get/";
        public static final String GETALL = RestUtil.URL + "/api/peoples/";
        public static final String GET_BY_EMAIL = RestUtil.URL + "/api/people/email/";
    }

    public static class PersonRole {
        public static final String POST = RestUtil.URL + "/api/prole/post";
        public static final String GET_ID = RestUtil.URL + "/api/prole/get/";
        public static final String GETALL = RestUtil.URL + "/api/proles/";
    }


}
