package infoshare.services.storage.Impl;

import infoshare.domain.storage.FileResults;
import infoshare.restapi.storage.FileResultsAPI;
import infoshare.services.storage.FileResultsService;

/**
 * Created by THULEH on 2016/03/24.
 */
public class FileResultsServiceImpl implements FileResultsService {

    private static FileResultsServiceImpl fileResultsService =null;

    private FileResultsServiceImpl(){}

    public  static FileResultsServiceImpl getInstance(){
        if(fileResultsService ==null) {
            return new FileResultsServiceImpl();
        }
        return fileResultsService;
    }
    @Override
    public FileResults findById(String s) {
        return FileResultsAPI.findById(s);
    }

    @Override
    public FileResults save(FileResults entity) {
        return FileResultsAPI.save(entity);
    }


}
