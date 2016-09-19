package infoshare.services.utililties.Impl;



import infoshare.domain.storage.StorageUrl;
import infoshare.restapi.common.util.StorageUrlAPI;
import infoshare.services.utililties.StorageUrlService;

import java.util.Set;

/**
 * Created by hashcode on 2016/01/06.
 */
public class StorageUrlServiceImpl implements StorageUrlService {
    private static StorageUrlServiceImpl storageUrlService =null;

    private StorageUrlServiceImpl(){}

    public  static StorageUrlServiceImpl getInstance(){
        if(storageUrlService ==null) {
            return new StorageUrlServiceImpl();
        }
        return storageUrlService;
    }
    @Override
    public StorageUrl save(StorageUrl link) {
        return StorageUrlAPI.save(link);
    }

    @Override
    public StorageUrl getById(String id) {
        return StorageUrlAPI.getById(id);
    }

    @Override
    public Set<StorageUrl> getAllLinks() {
        return StorageUrlAPI.getAllLinks();
    }
}
