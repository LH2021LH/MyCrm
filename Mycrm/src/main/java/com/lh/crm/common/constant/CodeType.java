package com.lh.crm.common.constant;

/**
 * @description: TODO
 * @author 胡淞纯
 * @date 2023/4/3 22:32
 * @version 1.0
 */
public enum CodeType {
    RETURN_OBJECT_CODE_FAILER_PASSWORDANDUSERNAME("0","用户名或者密码错误"),
    RETURN_OBJECT_CODE_FAILER_USER_EXPIRATION("1","用户过期"),
    RETURN_OBJECT_CODE_FAILER_STUCT_LOCKED("2","用户状态被锁定"),
    RETURN_OBJECT_CODE_FAILER_IP_LIMITED("3","IP受限"),
    RETURN_OBJECT_CODE_SUCCEED("4","验证成功");

    private String code;
    private String msg;
    CodeType(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    /** 
     * @description: 根据code值返回,CodeType 实例对象 
     * @param: code 
     * @return: com.lh.crm.common.constant.CodeType 
     * @author 胡淞纯
     * @date: 2023/4/3 22:47
     */ 

    public static CodeType fromTypeName(String code) {
        for (CodeType type : CodeType.values()) {
            if (type.code().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
