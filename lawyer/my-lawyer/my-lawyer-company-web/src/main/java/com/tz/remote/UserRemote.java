package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
import com.tz.res.AppMsgResult;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name= "my-lawyer-user-service",configuration=FeignConfiguration.class, fallback = UserRemoteHystrix.class)
public interface UserRemote {

    @RequestMapping(value = "company/api/test")
    public String test(@RequestParam(value = "name") String name);
    //获取用户信息
    @GetMapping("company/api/getUserList")
	public AppMsgResult getUserList(@RequestParam(value = "curPage",required=true,defaultValue="1") Integer curPage,@RequestParam(value = "rows",required=false) Integer rows) ;

}
