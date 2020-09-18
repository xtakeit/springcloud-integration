package cn.edu.dgut.intergration.storage.impl;

import cn.edu.dgut.intergration.api.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author moon
 * dubbo service
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public Boolean storageCall() {
        log.info("==storagetCall==");
        return true;
    }
}
