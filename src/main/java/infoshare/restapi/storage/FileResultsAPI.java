package infoshare.restapi.storage;

import infoshare.app.conf.RestUtil;
import infoshare.domain.storage.FileResults;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by THULEH on 2016/03/24.
 */
public class FileResultsAPI {
    public static Set<FileResults> save(String url){
        try {
            return  new HashSet<FileResults>();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static FileResults findById(String id){
        return RestUtil.getById(UploadBaseURL.Media.GET,id,FileResults.class);
    }
}
