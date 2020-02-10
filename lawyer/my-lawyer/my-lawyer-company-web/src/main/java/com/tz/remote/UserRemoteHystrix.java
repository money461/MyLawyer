package com.tz.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/15.
 */
@Component
public class UserRemoteHystrix implements UserRemote{

    @Override
    public String test(@RequestParam(value = "name") String name) {
        return "hello " +name+", this messge send failed ";
    }

	@Override
	public AppMsgResult getUserList(Integer curPage, Integer rows) {
		// TODO Auto-generated method stub
		return AppMsgResult.result(500, "服务器连接超时，请稍后重试！", "");
	}
}
