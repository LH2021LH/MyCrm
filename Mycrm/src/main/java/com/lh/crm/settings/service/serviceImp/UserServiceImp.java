package com.lh.crm.settings.service.serviceImp;

import com.lh.crm.common.constant.CodeType;
import com.lh.crm.common.untils.CntDayTime;
import com.lh.crm.settings.domain.User;
import com.lh.crm.settings.mapper.UserMapper;
import com.lh.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Service
 * @author 胡淞纯
 * @date 2023/4/3 12:15
 * @version 1.0
 */

@Service("userService")
public class UserServiceImp implements UserService {
    private UserMapper userMapper;

    public UserServiceImp() {
    }

    @Autowired
    public UserServiceImp(@Qualifier("userMapper") UserMapper userMapper) {
        this.userMapper = userMapper;
    }



    /**
     * 如果密码账号错误:0
     * 如果用户过期:1
     * 用户状态被锁定:2
     * ip受限:3
     * 完全正确:4
     * 可以优化一下: 将可能出现的概率大的 数字调大些 比如 成功>失败 -> 密码 > ip > locked > 过期
     * @description:
     * @param: null
     * @return:  Interger
     * @author 胡淞纯
     * @date: 2023/4/1 20:42
     */
    @Override
    public Map<String,Object> jutifyUserByPwdAndUsn(Map<String, Object> map) {
        Map<String, Object> verificationResul = new HashMap<String, Object>();
        verificationResul.put("code",CodeType.RETURN_OBJECT_CODE_SUCCEED.code());
//        String allowIpValue = (String)map.remove("allowIps");

        User user = userMapper.selectUserByPsdAndUserName(map);
        if(user == null){
            verificationResul.put("code",CodeType.RETURN_OBJECT_CODE_FAILER_PASSWORDANDUSERNAME.code());
        }else {

            String crnTime = CntDayTime.dayTime("yyyy-MM-dd HH:mm:ss");
            /**
             * !user.getAllowIps().equals(""): 这里是在检查数据库有没有设置allow_ip,
             * 如果没有就直接通过,否则需要进行判断 ===> 我设置的ip包括它访问的ip吗?
             * @description: 这里实现了校验功能
             * @param: map
             * @return: Integer
             * @author 胡淞纯
             * @date: 2023/4/3 12:10
             */
            if (crnTime.compareTo(user.getExpireTime()) > 0){
                verificationResul.put("code",CodeType.RETURN_OBJECT_CODE_FAILER_USER_EXPIRATION.code());
            } else if (!user.getAllowIps().equals("") && !user.getAllowIps().contains(map.get("allowIps").toString())){
                verificationResul.put("code", CodeType.RETURN_OBJECT_CODE_FAILER_IP_LIMITED.code());
            } else if ("0".equals(user.getLockState())){
                //0就是锁住了,1就是激活态
                verificationResul.put("code",CodeType.RETURN_OBJECT_CODE_FAILER_STUCT_LOCKED.code());
            }
        }
            verificationResul.put("userName",user.getName());
        return verificationResul;
    }

    @Override
    public List<String> queryAllUserNameList() {
        List<String> userList = userMapper.selectAllUserNameList();
        return userList;
    }
}
