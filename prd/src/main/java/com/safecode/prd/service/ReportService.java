package com.safecode.prd.service;

import java.util.List;

import com.safecode.prd.pojo.Report;

public interface ReportService {
	public List<Report> queryAll();
	public List<Report> queryReportByPage(Integer page,Integer rows);
	
}
