package infoshare.restapi.storage;

import infoshare.app.conf.RestUtil;
import infoshare.domain.demographics.Role;
import infoshare.domain.storage.FileResults;
import infoshare.restapi.Roles.RolesBaseUrl;

import java.util.Set;

/**
 * Created by THULEH on 2016/03/24.
 */
public class FileResultsAPI {
    public static FileResults save(FileResults results){
        return RestUtil.save(UploadBaseURL.Media.POST, results, FileResults.class);
    }
    public static FileResults findById(String id){
        return RestUtil.getById(UploadBaseURL.Media.GET,id,FileResults.class);
    }
}
