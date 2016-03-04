package infoshare.restapi.demographics;

import infoshare.app.conf.RestUtil;

/**
 * Created by Songezo on 2016-03-03.
 */
public class DemographicsBaseUrl {
    public static class Gender{
        public static final String POST = RestUtil.URL + "  /api/demographics/gender/post ";
        public static final String GET_ID = RestUtil.URL + "  /api/demographics/gender/get/";
        public static final String GET_ALL = RestUtil.URL + "  /api/demographics/genders/get";
    }

    public static class Language{
        public static final String POST = RestUtil.URL + "  /api/demographics/language/post";
        public static final String GET_ID = RestUtil.URL + "  /api/demographics/language/get/";
        public static final String GET_ALL = RestUtil.URL + "  /api/demographics/languages/get";
    }

    public static class LanguageProficiency{
        public static final String POST = RestUtil.URL + "  /api/demographics/languagep/post";
        public static final String GET_ID = RestUtil.URL + "  /api/demographics/languagep/get/";
        public static final String GET_ALL = RestUtil.URL + "  /api/demographics/languageps/get";
    }

    public static class Race{
        public static final String POST = RestUtil.URL + "  /api/demographics/race/post ";
        public static final String GET_ID = RestUtil.URL + "  /api/demographics/race/get/";
        public static final String GET_ALL = RestUtil.URL + "  /api/demographics/races/get";
    }

    public static class Roles{
        public static final String POST = RestUtil.URL + "  /api/demographics/role/post";
        public static final String GET_ID = RestUtil.URL + "  /api/demographics/role/get/";
        public static final String GET_ALL = RestUtil.URL + "  /api/demographics/roles/get";
    }
}
