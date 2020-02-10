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
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaCaseCategoryVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.res.AppMsgResult;
import com.tz.service.UserService;
import com.tz.service.ZaCaseCategoryService;;

/**
 * 律师分类
 * @author menglin
 * 2018年1月23日10:25:55
 *
 */
@RestController
@RequestMapping("admin/lawyer/api")
public class CaseCategoryController {

	@Autowired
	private ZaCaseCategoryService caseCategoryService;

	/**
	 * 查询所有的律师分类或者查询父类下的所有子类分类列表
	 * @param caseCategoryVo
	 * @param curPage
	 * @param rows
	 * @param userId
	 * @param token
	 * @param type
	 * @return
	 */
	@PostMapping("/findCategoryOrSonList")
	public AppMsgResult findCategoryOrSonList(@RequestBody ZaCaseCategoryVo caseCategoryVo, @RequestParam(required = false) Integer curPage,
			@RequestParam(required = false) Integer rows,
			@RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return caseCategoryService.findCategoryOrSonList(caseCategoryVo, curPage, rows, type, userId, token);
	}

	/**
	 * 添加或者修改律师分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @param type
	 * @return
	 */
	@PostMapping("/addOrUpdateCategory")
	public AppMsgResult addOrUpdateCategory(@RequestBody ZaCaseCategory caseCategory, @RequestParam(required = true) String type, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return caseCategoryService.addOrUpdateCategory(caseCategory, type, userId, token);
	}

	/**
	 * 根据id删除律师分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/deleteById")
	public AppMsgResult deleteById(@RequestParam(required = true) String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return caseCategoryService.deleteById(id, userId, token);
	}


	/**
	 * 根据id修改律师分类状态
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
		return caseCategoryService.updateStatusById(id, caseStatus, userId, token);
	}
	/**
	 * 根据id查询律师分类信息
	 * 
	 * @param user
	 * @param userId
	 * @param token
	 * @return
	 */
	@PostMapping("/selectById")
	public AppMsgResult selectById(@RequestParam(required = true) String id, @RequestParam(required = true) String userId,
			@RequestParam(required = true) String token) {
		return caseCategoryService.selectById(id, userId, token);
	}


}
