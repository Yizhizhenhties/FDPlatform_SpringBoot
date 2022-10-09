package org.example.fdplatform.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.fdplatform.mapper.UserMapper;
import org.example.fdplatform.model.domain.User;
import org.example.fdplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    * :return
    *   state: 0: success 1: failed_with_no_account 2:failed_with_wrong_password
    *   user
    * */
    @Override
    public Map verityPasswd(String account, String password) {
        Map<String, Object> re = new HashMap<>();
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("account", account);
        if(userMapper.selectCount(queryWrapper) > 0){
            queryWrapper.eq("password", password);
            User user = userMapper.selectOne(queryWrapper);
            if(user == null) re.put("state", 2);
            else{
                re.put("state", 0);
                re.put("user", user);
            }
        }
        else re.put("state", 1);
        return re;
    }
}
