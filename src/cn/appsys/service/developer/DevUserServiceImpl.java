package cn.appsys.service.developer;

import cn.appsys.dao.DevUser.DevUserMapper;
import cn.appsys.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bee
 * @Date: 2018/11/26 22:59
 * @Description:
 */
@Service
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public DevUser login(String devCode, String devPassword) {
        DevUser user = null;
        user = devUserMapper.getLoginUser(devCode);
        //匹配密码
        if(null != user){
            if(!user.getDevPassword().equals(devPassword))
                user = null;
        }
        return user;
    }
}
