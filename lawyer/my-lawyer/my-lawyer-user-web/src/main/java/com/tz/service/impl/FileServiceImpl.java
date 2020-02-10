package com.tz.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.plaf.synth.SynthStyle;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tz.cache.RedisCache;
import com.tz.res.AppMsgResult;
import com.tz.res.Constant;
import com.tz.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// fast 文件上传
	@Autowired
	private FastDFSClientWrapper dfsClientWrapper;

	@Autowired
	private RedisCache cache;
	@Override
	public AppMsgResult validateUserLogin(String userId, String token,String type) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		if (StringUtils.isBlank(userId)) {
			return AppMsgResult.result(538, "登录用户的id不能为空！", null);
		}
		if (StringUtils.isBlank(token)) {
			return AppMsgResult.result(7000, "登录token不能为空！", null);
		}
		if (StringUtils.isBlank(type)) {
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		// 获取后台管理用户的登录 token
		String cache_key = "";
		int cache_time = 0;
		if("admin".equals(type)) {
			cache_key = RedisCache.CAHCEADMIN + RedisCache.CAHCENAME_ADMIN+ userId;
			cache_time = RedisCache.ADMINCAHCETIME;
		}else if("app".equals(type)) {
			cache_key = RedisCache.CAHCENAME + RedisCache.CAHCENAME_APP+ userId;
			cache_time = RedisCache.USERCAHCETIME;
		}else{
			return AppMsgResult.result(529, "Type类型参数错误！", null);
		}
		String result_cache = cache.getCache(cache_key, String.class);
		// 是否存在
		if (null == result_cache) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		// 验证
		if (!result_cache.equals(token)) {
			return AppMsgResult.result(6000, "用户未登录！", null);
		}
		// 重置userToken有效时间
		cache.putCacheWithExpireTime(cache_key, result_cache, cache_time);
		msgResult = AppMsgResult.result(200, "success", null);
		return msgResult;
	}
	
	@Override
	public AppMsgResult deleteFile(String fileUrl,String type,String userId,String token) {
		// TODO Auto-generated method stub
		AppMsgResult msgResult = null;
		System.out.println(fileUrl);
		msgResult = validateUserLogin(userId,token,type);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}	
		// 修改
		try {
			if (fileUrl.indexOf(Constant.FILESERVER_URL) != -1) {
				fileUrl = fileUrl.substring(fileUrl.indexOf(Constant.FILESERVER_URL) + Constant.FILESERVER_URL.length(),
						fileUrl.length());
			}
			String url = fileUrl.split("\\?")[0];
			dfsClientWrapper.deleteFile(url);
			//图片文件处理其他规格
		/*	String[] url_150 = url.split("\\.");
			dfsClientWrapper.deleteFile(url_150[0] + "_150x150." + url_150[1]);*/
			LOG.info("deleteFile--------");
			msgResult = AppMsgResult.result(200, "success", null);
		} catch (Exception e) {
			// TODO: handle exception
			msgResult = AppMsgResult.result(5000, "操作失败，请稍后重试！", null);
		}	
				
		return msgResult;
	}

	@Override
	public AppMsgResult uploadFile(MultipartFile file,String type,String userId,String token) {
		AppMsgResult msgResult = null;
		// 判断空
		msgResult = validateUserLogin(userId,token,type);
		if (200 != (int) msgResult.getFlag()) {
			return msgResult;
		}	
		// 上传
		try {
			LOG.info("size--------" + file.getSize() + "?" + file.getOriginalFilename());
			/*String path = dfsClientWrapper.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
					file.getOriginalFilename());*/
			String path = dfsClientWrapper.uploadFile(file.getInputStream(), file.getSize(),file.getOriginalFilename());
			LOG.info("uploadImageAndCrtThumbImage--------" + path + "?" + file.getOriginalFilename());
			if (StringUtils.isNotBlank(path)) {
				msgResult = AppMsgResult.result(200, "success", "" + path + "?" + file.getOriginalFilename());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msgResult = AppMsgResult.result(5000, "服务器繁忙，请稍后重试！", null);
		}		
		return msgResult;
	}
}
