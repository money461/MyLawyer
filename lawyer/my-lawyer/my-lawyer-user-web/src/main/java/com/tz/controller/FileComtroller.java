package com.tz.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tz.res.AppMsgResult;
import com.tz.service.FileService;

/**
 * 文件类
 * 
 * @author menglin 2017年12月26日17:37:55
 */

@RestController
@RequestMapping("/user/api")
public class FileComtroller {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 文件服务
	@Autowired
	private FileService fileService;
	
	/**
	 * 文件上传
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/uploadFile")
	public AppMsgResult uploadFile(@RequestParam("file") MultipartFile file,String type,String userId,String token) throws IOException {
		LOG.info("invoke----------/user/uploadTest");
		return fileService.uploadFile(file,type,userId,token);
		
	}
	
	/**
	 * 删除文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/deleteFile")
	public AppMsgResult deleteFile(String fileUrl,String type,String userId,String token) throws IOException {
		LOG.info("invoke----------/user/deleteFile");
		return fileService.deleteFile(fileUrl,type,userId,token);
		
	}

}
