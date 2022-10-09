package org.example.fdplatform.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "validateimages")
@Data
public class ValidateImgGroup {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String imguids;
    private String answer;
    private String groupuuid;
}
