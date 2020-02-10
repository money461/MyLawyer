package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaPlatAuthority;
import com.tz.pojo.admin.ZaPlatAuthorityVo;
import com.tz.remote.PlatAuthorityRemote;
import com.tz.res.AppMsgResult;

@RestController
@RequestMapping("admin/api")
public class PlatAuthorityController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlatAuthorityRemote platAuthorityRemote;
	
	/**
	 * 查询所有未被冻结的权限
	 * @param status 0未被冻结
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/selectPlatAuthUsable")
	public AppMsgResult selectPlatAuthUsable(Integer status,@RequestParam("userId") String userId,@RequestParam("token") String token) {
		LOG.info("invoke-----------selectPlatRoleUsable");
		AppMsgResult result=platAuthorityRemote.selectPlatAuthUsable(status,userId,token);
		return result;
	}
	
	
	/**
	 * 添加或者修改权限
	 * @param zaPlatAuthority
	 * @param type
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/addOrUpdateAuth")
	public AppMsgResult addOrUpdateAuth( ZaPlatAuthority zaPlatAuthority,@RequestParam(required=true) String type,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------addOrUpdateAuth");
		AppMsgResult result=platAuthorityRemote.addOrUpdateAuth(zaPlatAuthority,type,userId,token);
		return result;
		
	}
	
	/**
	 * 获取权限列表信息
	 * @param zaPlatAuthorityVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getPlatAuthorityList")
	public AppMsgResult getPlatAuthorityList( ZaPlatAuthorityVo zaPlatAuthorityVo, Integer curPage, Integer rows ,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------getPlatAuthorityList");
		AppMsgResult result=platAuthorityRemote.getPlatAuthorityList(zaPlatAuthorityVo,curPage,rows,userId,token);
		return result;
		
	}
	
	
	/**
	 * 根据id查询指定权限
	 * @return
	 */
	@GetMapping("/getPlatAuthorityById")
	public AppMsgResult getPlatAuthorityById(@RequestParam(required=true)String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------getPlatAuthorityById");
		AppMsgResult result=platAuthorityRemote.getPlatAuthorityById(id,userId,token);
		return result;
		
	}
	
	/**
	 * 删除权限
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delPlatAuthorityById")
	public AppMsgResult delPlatAuthorityById(@RequestParam(required=true)String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delPlatAuthorityById");
		AppMsgResult result=platAuthorityRemote.delPlatAuthorityById(id,userId,token);
		return result;
		
	}
	
	
	/**
	 * 权限冻解
	 * @param id
	 * @param status
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/authorityFreezeById")
	public AppMsgResult authorityFreezeById(@RequestParam(required=true)String id,@RequestParam(required=true) Integer status,@RequestParam(required=true) String userId,@RequestParam(required=true) String token) {
		LOG.info("invoke-----------authorityFreezeById");
		AppMsgResult result=platAuthorityRemote.authorityFreezeById(id,status,userId,token);
		return result;
		
	}

}
