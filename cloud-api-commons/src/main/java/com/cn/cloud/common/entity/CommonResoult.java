package com.cn.cloud.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class CommonResoult<T> {
    private Integer code;
    private String message;
    private T  data;
    public CommonResoult(Integer code,String message){
        //this(code,message);
        this.code=code;
        this.message=message;
        this.data=null;
    }

    public CommonResoult(Integer code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }
}
