package com.tz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 平台角色信息WEB层
 * @author QL
 *
 */

import com.tz.pojo.ZaPlatRole;
import com.tz.pojo.vo.ZaPlatRoleVo;
import com.tz.res.AppMsgResult;
import com.tz.service.PlatRoleService;
@RestController
@RequestMapping("admin/api")
public class PlatRoleController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlatRoleService platRoleService;
	

	/**
	 * 查询所有未被冻结的角色
	 * @param status 0未被冻结
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/selectPlatRoleUsable")
	public AppMsgResult selectPlatRoleUsable(Integer status,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------selectPlatRoleUsable");
		AppMsgResult result=platRoleService.selectPlatRoleUsable(status,userId,token);
		return result;
	}
	
	/**
	 *添加或者更新角色信息 
	 * @param zaPlatRole
	 * @param authIds
	 * @param type
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/addOrUpdaterPlatRole")
	public AppMsgResult addOrUpdaterPlatRole(@RequestBody ZaPlatRole zaPlatRole,String authIds,@RequestParam(required=true) String type,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------addOrUpdatePlatManager");
		AppMsgResult result=platRoleService.addOrUpdaterPlatRole(zaPlatRole,authIds,type,userId,token);
		return result;
		
	}
	
	/**
	 * 根据id查询角色信息
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryPlatRoleById")
	public AppMsgResult queryPlatRoleById(@RequestParam(required=true)String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------queryPlatRoleById");
		AppMsgResult result=platRoleService.queryPlatRoleById(id,userId,token);
		return result;
		
	}
	
	/**
	 * 查询角色列表信息
	 * @param zaPlatRoleVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getPlatRoleList")
	public AppMsgResult getPlatRoleList(@RequestBody ZaPlatRoleVo zaPlatRoleVo ,Integer curPage, Integer rows ,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------getPlatRoleList");
		AppMsgResult result=platRoleService.getPlatRoleList(zaPlatRoleVo,curPage,rows,userId,token);
		return result;
		
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delPlatRoleById")
	public AppMsgResult delPlatRoleById(@RequestParam(required=true)String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delPlatRoleById");
		AppMsgResult result=platRoleService.delPlatRoleById(id,userId,token);
		return result;
	}
	
	
	/**
	 * 冻解角色
	 * @param id
	 * @param status 0解冻 1 冻结
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/roleFreezeById")
	public AppMsgResult roleFreezeById(@RequestParam(required=true)String id,@RequestParam(required=true) Integer status,@RequestParam(required=true) String userId,@RequestParam(required=true) String token) {
		LOG.info("invoke-----------roleFreezeById");
		AppMsgResult result=platRoleService.roleFreezeById(id,status,userId,token);
		return result;
		
	}
	
}
