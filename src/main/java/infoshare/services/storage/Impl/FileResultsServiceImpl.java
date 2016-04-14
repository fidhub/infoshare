package infoshare.services.storage.Impl;

import infoshare.domain.storage.FileResults;
import infoshare.restapi.storage.FileResultsAPI;
import infoshare.services.storage.FileResultsService;

import java.util.Set;

/**
 * Created by THULEH on 2016/03/24.
 */
public class FileResultsServiceImpl implements FileResultsService {

    private static FileResultsServiceImpl fileResultsService =null;

    private FileResultsServiceImpl(){}

    public  static FileResultsServiceImpl getInstance(){
        if(fileResultsService ==null) {
            fileResultsService = new FileResultsServiceImpl();
        }
        return fileResultsService;
    }

    @Override
    public Set<FileResults> save(String entity) {
        return FileResultsAPI.save(entity);
    }


}
