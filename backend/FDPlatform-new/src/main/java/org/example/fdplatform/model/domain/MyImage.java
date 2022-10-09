package org.example.fdplatform.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "image")
@Data
public class MyImage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String imguid;
    private String base64url;
}
