package com.tz.service;

import org.springframework.web.multipart.MultipartFile;

import com.tz.res.AppMsgResult;
import com.tz.res.MsgResult;

public interface FileService {
	

	// 登录公共验证
	AppMsgResult validateUserLogin(String userId, String token,String type);
	//用户上传头像
	AppMsgResult uploadFile(MultipartFile file,String type,String userId,String token);
	//删除垃圾图片
	AppMsgResult deleteFile(String fileUrl,String type,String userId,String token);
	
 
}
