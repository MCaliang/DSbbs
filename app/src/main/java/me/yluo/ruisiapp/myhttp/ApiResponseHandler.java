package me.yluo.ruisiapp.myhttp;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;

import me.yluo.ruisiapp.api.entity.ApiResult;

public abstract class ApiResponseHandler<T> extends ResponseHandler {

    @Override
    public void onSuccess(byte[] response) {
        //Result<User> obj = (Result<User>) JSON.parseObject(js, new TypeReference<Result<User>>(){});
        final  Type type = new TypeReference<ApiResult<T>>() {}.getType();
        ApiResult<T> res = JSON.parseObject(response, type);
        if (res == null) {
            onFailure(new Throwable("parse json error"));
        } else {
            onSuccess(res);
        }

    }

    public abstract void onSuccess(ApiResult<T> result);
}
