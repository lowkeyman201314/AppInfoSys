package cn.appsys.dao.DevUser;

import cn.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: bee
 * @Date: 2018/11/26 22:50
 * @Description:
 */
public interface DevUserMapper {

    /**
     * 根据devCode获取用户记录
     * @param devCode
     * @return
     */
    public DevUser getLoginUser(@Param("devCode") String devCode);
}
