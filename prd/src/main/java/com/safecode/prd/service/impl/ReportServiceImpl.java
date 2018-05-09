package com.safecode.prd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.safecode.prd.mapper.ReportMapper;
import com.safecode.prd.pojo.Report;
import com.safecode.prd.service.ReportService;

@Service("reportServiceImpl")
public class ReportServiceImpl implements ReportService{
	
	@Resource
	private ReportMapper reportMapper;
	public List<Report> queryAll() {
		List<Report> list = reportMapper.queryAll();
		return list;
	}
	public List<Report> queryReportByPage(Integer page,Integer rows) {
		PageHelper.startPage(page, rows);
		List<Report> list = reportMapper.select(null);
		return list;
	}

	

}
