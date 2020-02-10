package com.tz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tz.pojo.ZaComCategory;
import com.tz.pojo.ZaUser;
import com.tz.pojo.vo.ZaAdminUserVo;
import com.tz.pojo.vo.ZaComAuthenticationVo;
import com.tz.pojo.vo.ZaComCategoryVo;
import com.tz.pojo.vo.ZaLawyerAuthenticationVo;
import com.tz.remote.ComCategoryRemote;
import com.tz.remote.UserRemote;
import com.tz.res.AppMsgResult;


/**
 * 企业分类
 * @author menglin
 *
 */
@RestController
@RequestMapping("/admin/com/api")
public class ComCategoryContrller {


    @Autowired
    ComCategoryRemote comCategoryRemote;
	
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
	public AppMsgResult findCategoryOrSonList(ZaComCategoryVo comCategoryVo,Integer curPage,Integer rows,String type,String userId,String token) {
		return comCategoryRemote.findCategoryOrSonList(comCategoryVo, curPage, rows, type, userId, token);
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
	public AppMsgResult addOrUpdateCategory(ZaComCategory comCategory,String type,String userId, String token) {
		return comCategoryRemote.addOrUpdateCategory(comCategory, type,userId, token);
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
	public AppMsgResult deleteById(String id,String userId,String token) {
		return comCategoryRemote.deleteById(id, userId, token);
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
	public AppMsgResult updateStatusById(String id,String caseStatus,String userId,String token) {
		return comCategoryRemote.updateStatusById(id, caseStatus, userId, token);
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
	public AppMsgResult selectById(String id,String userId,String token) {
		return comCategoryRemote.selectById(id, userId, token);
	}
 
}
