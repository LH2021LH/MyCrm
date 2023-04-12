package com.lh.crm.common.codedomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCode {
    private String code;
    private String msg;
    public Object other;

    public UserCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
