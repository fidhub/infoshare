package infoshare.restapi.storage;

import infoshare.app.conf.RestUtil;
import infoshare.domain.storage.FileResults;

/**
 * Created by THULEH on 2016/03/24.
 */
public class UploadBaseURL {
    public static class Media{
       public static final String POST= RestUtil.URL+"/api/upload"; // @controllers.storage.FilesController.upload
       public static final String GET =RestUtil.URL+"/api/static/"; //:id/:filename @controllers.storage.FilesController.getFile(id,filename)
    }
}
