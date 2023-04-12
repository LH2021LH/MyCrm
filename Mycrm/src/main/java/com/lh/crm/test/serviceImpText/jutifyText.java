package com.lh.crm.test.serviceImpText;

import com.lh.crm.settings.domain.User;
import com.lh.crm.settings.mapper.UserMapper;
import com.lh.crm.settings.service.UserService;
import com.lh.crm.settings.service.serviceImp.UserServiceImp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


public class jutifyText {



    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginAct","1234");
        map.put("loginPwd","11211");
        map.put("allowIps","198.1212");

        ApplicationContext context = new
                ClassPathXmlApplicationContext("SpringConfig/ApplicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User user = userMapper.selectByPrimaryKey("06f5fc056eac41558a964f96daa7f27c");
        System.out.println(user.getExpireTime());

/*        System.out.println("context里的对象:" + userService.getClass() + "hashCode:" +
                userService.hashCode() +
                "" + userService);*/
   /*     UserService userServiceImp = new UserServiceImp();
        System.out.println("new的对象:" + userServiceImp.getClass()
        + "  " + userServiceImp.jutifyUserByPwdAndUsn(map));
*/
    }
}
