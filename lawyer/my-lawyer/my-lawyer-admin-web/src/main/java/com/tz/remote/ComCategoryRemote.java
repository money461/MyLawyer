package com.tz.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tz.config.FeignConfiguration;
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

/**
 * 后台企业远程连接
 * @author menglin
 *
 */
@FeignClient(name = "my-lawyer-admin-service",configuration=FeignConfiguration.class, fallback = ComCategoryRemoteHystrix.class)
public interface ComCategoryRemote {

	// 查询所有的企业分类或者查询父类下的所有子类分类列表
	@PostMapping("admin/com/api/findCategoryOrSonList")
	public AppMsgResult findCategoryOrSonList(ZaComCategoryVo comCategoryVo,
			@RequestParam(value = "curPage", required = false) Integer curPage,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 添加或者修改企业分类信息
	@PostMapping("admin/com/api/addOrUpdateCategory")
	public AppMsgResult addOrUpdateCategory(ZaComCategory comCategory,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 根据id删除企业分类信息
	@PostMapping("admin/com/api/deleteById")
	public AppMsgResult deleteById(@RequestParam(value = "userId", required = true) String id,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);

	// 根据id修改企业分类状态
	@PostMapping("admin/com/api/updateStatusById")
	public AppMsgResult updateStatusById(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "caseStatus", required = true) String caseStatus,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);
	// 查询分类信息
	@PostMapping("admin/com/api/selectById")
	public AppMsgResult selectById(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "token", required = true) String token);	
}
