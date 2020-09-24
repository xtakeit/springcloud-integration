package cn.edu.dgut.integration.storage.service.impl;

import cn.edu.dgut.integration.api.StorageApi;
import cn.edu.dgut.integration.common.service.impl.BaseServiceImpl;
import cn.edu.dgut.integration.model.Storage;
import cn.edu.dgut.integration.storage.dao.StorageDao;
import cn.edu.dgut.integration.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author moon
 * dubbo service
 * dubbo开放给外部的api必须在前面，或者直接指定serivce
 */
@Slf4j
@Service(interfaceClass = StorageApi.class)
public class StorageServiceImpl extends BaseServiceImpl<Storage> implements StorageService, StorageApi {

    private StorageDao storageDao;

    @Resource
    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
        super.setBaseMapper(storageDao);
    }

    @Override
    public Boolean reduce(Long storageId, Long count) {
        Integer row = storageDao.reduceStorageCount(storageId, count);
        return judgeRowNotEqualsToZero(row);
    }

    @Override
    public Storage selectByStorageId(Long storageId) {
        return storageDao.selectById(storageId);
    }


}
