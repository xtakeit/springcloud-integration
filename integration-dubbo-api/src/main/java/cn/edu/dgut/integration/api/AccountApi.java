package cn.edu.dgut.integration.api;

public interface AccountApi{

    Boolean reduce(Long userId, Double money);

    Boolean add(Long userId, Double money);

}



