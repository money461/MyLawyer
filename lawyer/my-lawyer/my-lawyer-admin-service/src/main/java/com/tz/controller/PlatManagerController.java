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

import com.tz.pojo.ZaPlatManager;
import com.tz.pojo.vo.ZaPlatManagerVo;
import com.tz.res.AppMsgResult;
import com.tz.service.PlatManagerService;

/**
 * 管理者WEB请求处理层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("admin/api")
public class PlatManagerController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlatManagerService platManagerService;
	/**
	 * 注册添加或者更新管理员
	 * @param platManager
	 * @param roleIds 角色id 逗号分隔的字符串
	 * @param type 注册添加"add" 更新"update"
	 * @param userId
	 * @param userToken
	 * @return
	 */
	@PostMapping("/addOrUpdaterPlatManager")
	public AppMsgResult addPlatManager(@RequestBody ZaPlatManager platManager,String roleIds,@RequestParam(required=true) String type,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------addOrUpdatePlatManager");
		AppMsgResult result=platManagerService.addOrUpdatePlatManager(platManager,roleIds,type,userId, token);
		return result;
	}

	/**
	 * 分页查询平台管理员列表信息
	 */
	
	@PostMapping("/getPlatManagerList")
	public AppMsgResult getPlatManagerList(@RequestBody ZaPlatManagerVo zaPlatManagerVo,Integer curPage, Integer rows) {
		LOG.info("invoke-----------getPlatManagerList");
		AppMsgResult result=platManagerService.getPlatManagerList(zaPlatManagerVo,curPage,rows);
		return result;
	}
	
	/**
	 * 根据id查询管理员信息
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryPlatManagerById")
	public AppMsgResult queryPlatManagerById(@RequestParam(required=true) String id,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------queryPlatManagerById");
		AppMsgResult result=platManagerService.queryPlatManagerById(id,userId,token);
		return result;
	}
	
	/**
	 * 删除管理员信息
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delManagerById")
	public AppMsgResult delManagerById(@RequestParam(required=true)String id,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delManagerById");
		AppMsgResult result=platManagerService.delManagerById(id,userId,token);
		return result;
	}
	
	/**
	 * 账户冻解账户
	 * @param id
	 * @param status 0冻结 1正常
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/accountFreezeById")
	public AppMsgResult accountFreezeById(@RequestParam(required=true)String id,@RequestParam(required=true)Integer status,@RequestParam(required=true)String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------accountFreezeById");
		AppMsgResult result=platManagerService.accountFreezeById(id,status,userId,token);
		return result;
	}
	
}
