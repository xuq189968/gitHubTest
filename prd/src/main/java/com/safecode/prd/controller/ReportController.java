package com.safecode.prd.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safecode.prd.pojo.Report;
import com.safecode.prd.service.ReportService;
import com.safecode.prd.util.CodeUtils;

@Controller
@RequestMapping("report")
public class ReportController {
	private final static Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Resource
	private ReportService reportService;

	@RequestMapping("list")
	@ResponseBody
	public List<Report> list(HttpServletRequest request,HttpServletResponse response) {
		setCors(request,response);
		List<Report> list = reportService.queryAll();
		return list;
	}

	@RequestMapping("queryReportByPage/{page}/{rows}")
	@ResponseBody
	public List<Report> queryReportByPage(@PathVariable Integer page, @PathVariable Integer rows,
			HttpServletRequest request,HttpServletResponse response) {
		setCors(request,response);
		List<Report> list = reportService.queryReportByPage(page, rows);
		return list;
	}

	@RequestMapping("downLoad/{id}")
	public void downLoad(@PathVariable String id, HttpServletRequest req, HttpServletResponse res) {
		BufferedInputStream bis = null;
		OutputStream os = null;
		/*
		 *打成jar包从classes/路劲下找
		 *不打File file = ResourceUtils.getFile("classpath:report/1_77212");
		 */
		try {
			String header = req.getHeader("User-Agent");
			InputStream stream =null;
			if ("0".equals(id)) {
				//file = ResourceUtils.getFile("classpath:report/1_77212");
				stream = getClass().getClassLoader().getResourceAsStream("report/1_77212.pdf");
			} else if ("1".equals(id)) {
				stream = getClass().getClassLoader().getResourceAsStream("report/2_35557.pdf");
			} else if ("2".equals(id)) {
				stream = getClass().getClassLoader().getResourceAsStream("report/3_27917.pdf");
			} else {
				stream = getClass().getClassLoader().getResourceAsStream("report/1_77212.pdf");
			}
		
			res.setHeader("content-type", "application/octet-stream");
			res.setContentType("application/octet-stream");
//			if (header.contains("Firefox")) {// 判断当前用户使用的是什么浏览（是不是火狐）
//				// 是，则使用base64
//				fileName = CodeUtils.base64EncodeFileName(fileName);
//			} else {
//				// 否，则使用URLEncode
//				fileName = URLEncoder.encode(fileName, "UTF-8");
//			}
			Integer parseInt = Integer.parseInt(id)+1;
			res.setHeader("Content-Disposition", "attachment;filename=" +parseInt.toString()+".pdf" );
			byte[] buff = new byte[1024];

			os = res.getOutputStream();
			bis = new BufferedInputStream(stream);
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("SUCCESS");
	}

	@RequestMapping("preview/{id}")
	public void preview(@PathVariable String id, HttpServletResponse res) {
		File file = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {                                                               //src/main/resources/report
			
			InputStream stream =null;
			if ("0".equals(id)) {
				//file = ResourceUtils.getFile("classpath:report/1_77212");
				stream = ReportController.class.getClassLoader().getResourceAsStream("report/1_77212.pdf");
			} else if ("1".equals(id)) {
				stream = getClass().getClassLoader().getResourceAsStream("report/2_35557.pdf");
			} else if ("2".equals(id)) {
				stream = getClass().getClassLoader().getResourceAsStream("report/3_27917.pdf");
			} else {
				stream = getClass().getClassLoader().getResourceAsStream("report/1_77212.pdf");
			}
			byte[] buff = new byte[1024];
			os = res.getOutputStream();
			bis = new BufferedInputStream(stream);
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 *  使用cors规避跨域请求
	 */
	public void  setCors(HttpServletRequest request,HttpServletResponse response){
		String header = request.getHeader("Origin");
		if(header==null){
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}else{ //说明是浏览器发出cors请求
			response.setHeader("Access-Control-Allow-Origin", "*");
		}
		
	}
}
