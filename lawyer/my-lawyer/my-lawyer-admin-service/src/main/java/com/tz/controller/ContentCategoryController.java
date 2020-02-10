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

import com.tz.pojo.ZaContentCategory;
import com.tz.pojo.admin.ZaContentCategoryAdminVo;
import com.tz.res.AppMsgResult;
import com.tz.service.ContentCategoryService;

@RestController
@RequestMapping("admin/content/api")
public class ContentCategoryController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	

	/**
	 * 分页获取所有的广告内容分类下的子类信息
	 * @param zaContentCategoryAdminVo
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/getContentCategoryList")
	public AppMsgResult getContentCategoryList(@RequestBody ZaContentCategoryAdminVo zaContentCategoryAdminVo,Integer curPage,Integer rows, @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------getContentCategoryList");
		AppMsgResult result=contentCategoryService.getContentCategoryList(zaContentCategoryAdminVo,curPage,rows,userId,token);
		return result;
	}
	
		/**
		 * 添加或者更新广告内容
		 * @param zaContentCategory
		 * @param userId
		 * @param token
		 * @return
		 */
	@PostMapping("/addOrUpdateContentCategory")
	public AppMsgResult addOrUpdateContentCategory(@RequestBody ZaContentCategory zaContentCategory,@RequestParam(required = true) String type, @RequestParam(required = true) String userId,@RequestParam(required = true)String token) {
		LOG.info("invoke-----------addOrUpdateContentCategory");
		AppMsgResult result=contentCategoryService.addOrUpdateContentCategory(zaContentCategory,type,userId,token);
	   return result;
	}
	
	/**
	 * 根据id查询广告内容信息
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping("/queryContentCategoryById")
	public AppMsgResult queryContentCategoryById(@RequestParam(required=true) Integer id,@RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------queryContentCategory");
		AppMsgResult result=contentCategoryService.queryContentCategoryById(id,userId,token);
		return result;
		
	}
	
	/**
	 * 停用或者启用广告内容分类
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/disOrEnableContentCategoryById")
	public AppMsgResult disOrEnableContentCategoryById(@RequestParam(required=true) Integer id,@RequestParam(required=true) Integer status, @RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------disOrEnableContentCategoryById");
		AppMsgResult result=contentCategoryService.disOrEnableContentCategoryById(id,status,userId,token);
		return result;
	}
	
	/**
	 * 删除某一广告内容分类
	 * @param id
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/delContentCategoryById")
	public AppMsgResult delContentCategoryById(@RequestParam(required=true) Integer id, @RequestParam(required=true) String userId,@RequestParam(required=true)String token) {
		LOG.info("invoke-----------delContentCategoryById");
		AppMsgResult result=contentCategoryService.delContentCategoryById(id,userId,token);
		return result;
	}
	
	/**
	 * 
	 * 查询所有的广告内容分类
	 */
	@GetMapping("/selectAllContentCategory")
	public AppMsgResult selectAllContentCategory(Integer status,@RequestParam(required=true)  String userId,@RequestParam(required=true)  String token) {
		LOG.info("invoke-----------selectAllContentCategory");
		AppMsgResult result=contentCategoryService.selectAllContentCategory(status,userId,token);
		return result;
	}
}