package cn.appsys.service.developer;

import cn.appsys.pojo.DevUser;

/**
 * @Author: bee
 * @Date: 2018/11/26 22:58
 * @Description:
 */
public interface DevUserService {

    /**
     * 登录验证
     * @param devCode
     * @param devPassword
     * @return
     */
    public DevUser login(String devCode,String devPassword);
}
