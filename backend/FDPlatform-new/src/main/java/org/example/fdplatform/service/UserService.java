package org.example.fdplatform.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.fdplatform.model.domain.User;
import java.util.Map;

public interface UserService extends IService<User> {
    Map verityPasswd(String account, String password);
}
