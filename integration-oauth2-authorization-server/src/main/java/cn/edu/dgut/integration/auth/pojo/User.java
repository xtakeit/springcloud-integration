package cn.edu.dgut.integration.auth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("order_tbl")
public class User{

    @TableId(type= IdType.AUTO)
    private Long id;
    private String username;
    private String password;


}
