package cn.edu.dgut.integration.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("storage_tbl")
public class Storage implements Serializable {


  private static final long serialVersionUID = -2325627620868733108L;

  @TableId(type= IdType.AUTO)
  private Long storageId;
  private String commodityCode;
  private Long count;
  private Double money;

}
