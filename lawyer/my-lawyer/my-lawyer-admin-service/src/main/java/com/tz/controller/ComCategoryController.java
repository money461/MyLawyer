package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaCaseCategory;
import com.tz.pojo.ZaComAuthentication;
import com.tz.pojo.ZaComCategory;
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaComCategoryVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserService;
import com.tz.service.ZaComCategoryService;;

/**
 * 企业分类
 * @author menglin
 * 2018年1月23日10:26:17
 */
@RestController
@RequestMapping("admin/com/api")
public class ComCategoryController {

	@Autowired
	private ZaComCategoryService comCategoryService;

	/**
	 * 查询所有的企业分类或者查询父类下的所有子类分类列表
	 * @param caseCategoryVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @param type
	 * @return
	 */
	@PostMapping("/findCategoryOrSonList")
	public AppMsgResult findCategoryOrSonList(@RequestBody ZaComCategoryVo comCategoryVo, @RequestParam(required = false) Integer curPage,
			@RequestParam(required = false) Integer rows,
			@RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return comCategoryService.findCategoryOrSonList(comCategoryVo, curPage, rows, type, userId, token);
	}

	/**
	 * 添加或者修改企业分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @param type
	 * @return
	 */
	@PostMapping("/addOrUpdateCategory")
	public AppMsgResult addOrUpdateCategory(@RequestBody ZaComCategory comCategory, @RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return comCategoryService.addOrUpdateCategory(comCategory, type, userId, token);
	}

	/**
	 * 根据id删除企业分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/deleteById")
	public AppMsgResult deleteById(@RequestParam(required = true) String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return comCategoryService.deleteById(id, userId, token);
	}


	/**
	 * 根据id修改企业分类状态
	 * @param id
	 * @param status
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/updateStatusById")
	public AppMsgResult updateStatusById(@RequestParam(required = true) String id,
			@RequestParam(required = true) String caseStatus, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return comCategoryService.updateStatusById(id, caseStatus, userId, token);
	}
	
	/**
	 * 根据id查询企业分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/selectById")
	public AppMsgResult selectById(@RequestParam(required = true) String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return comCategoryService.selectById(id, userId, token);
	}


}
