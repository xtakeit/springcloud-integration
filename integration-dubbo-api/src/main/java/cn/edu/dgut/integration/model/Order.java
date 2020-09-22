package cn.edu.dgut.integration.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("order_tbl")
public class Order implements Serializable {

  private static final long serialVersionUID = -5484087259554199610L;

  @TableId(type=IdType.AUTO)
  private Long orderId;
  private Long userId;
  private Long storageId;
  private Long count;
  private Double money;
  private Integer status;

}
