package infoshare.services.storage;

import infoshare.domain.storage.FileResults;

import java.util.Set;

/**
 * Created by THULEH on 2016/03/24.
 */
public interface FileResultsService  {
     Set<FileResults> save(String url);
}
