package infoshare.services.storage;

import infoshare.domain.storage.FileResults;
import infoshare.restapi.storage.FileResultsAPI;
import infoshare.services.Services;

/**
 * Created by THULEH on 2016/03/24.
 */
public interface FileResultsService  {
     FileResults findById(String s);
     FileResults save(FileResults entity);
}
