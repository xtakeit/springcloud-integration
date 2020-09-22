package cn.edu.dgut.integration.storage.dao;


import cn.edu.dgut.integration.model.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface StorageDao extends BaseMapper<Storage> {


}
