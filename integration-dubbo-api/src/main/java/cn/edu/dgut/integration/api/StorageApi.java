package cn.edu.dgut.integration.api;

import cn.edu.dgut.integration.model.Storage;

public interface StorageApi {

    Boolean reduce(Long storageId, Long count);

    Storage selectByStorageId(Long storageId);
}