package org.example.fdplatform.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "history")
@Data
public class History {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String hisUuid;
    private String username;
    private Integer length;
    private Integer proLength;
    private String imguids;
    private Date createtime;
    private Date updatetime;
}
