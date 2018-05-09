package com.safecode.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.safecode.prd.pojo.Report;
@Mapper
public interface ReportMapper extends 
com.github.abel533.mapper.Mapper<Report>{
	
	public List<Report> queryAll();
	
}
