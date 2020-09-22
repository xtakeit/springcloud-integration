package cn.edu.dgut.integration.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("account_tbl")
public class Account implements Serializable {

    private static final long serialVersionUID = -6128253807912413448L;

    @TableId(type= IdType.AUTO)
    private Long accountId;
    private Long userId;
    private Double money;

}
