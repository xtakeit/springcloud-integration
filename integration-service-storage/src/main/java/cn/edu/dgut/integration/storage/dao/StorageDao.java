package cn.edu.dgut.integration.storage.dao;


import cn.edu.dgut.integration.model.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface StorageDao extends BaseMapper<Storage> {

    @Update("update storage_tbl set count=count - #{reduceCount} where storage_id = #{storageId} and (count - #{reduceCount}) >= 0 ")
    int reduceStorageCount(@Param("storageId") Long storageId, @Param("reduceCount") Long reduceCount);

}
