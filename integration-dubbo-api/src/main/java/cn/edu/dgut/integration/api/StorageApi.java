package cn.edu.dgut.integration.api;

public interface StorageApi {

    Boolean reduce(String commodityCode, Long count);

}