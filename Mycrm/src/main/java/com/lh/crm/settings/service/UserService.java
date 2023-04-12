package com.lh.crm.settings.service;

import java.util.List;
import java.util.Map;


public interface UserService {

    Map<String, Object> jutifyUserByPwdAndUsn(Map<String, Object> map);

    List<String> queryAllUserNameList();
}
