package org.example.fdplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.fdplatform.model.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
