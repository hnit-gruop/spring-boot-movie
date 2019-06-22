package com.yc.controller;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yc.bean.Movie;
import com.yc.bean.MovieType;
import com.yc.bean.Type;
import com.yc.service.impl.MovieServiceImpl;
import com.yc.util.Utils;

@Controller
public class FrameController {
	
	@Resource
	MovieServiceImpl msi;
	
//	@Resource
//	TypeServiceImpl tsi;
	
	@RequestMapping("manage")
	public String manage() {
		return "manage/frame";
	}
	
	@RequestMapping("allMovie")
	public ModelAndView getAllMovie(ModelAndView model,@RequestParam(defaultValue="1") int current) throws IllegalArgumentException, IllegalAccessException {
		model.setViewName("manage/allMovie");
		
		PageInfo<Movie> l = msi.findAllMoive(current);
		
		List<Map<String,Object>> list = new ArrayList<>();
		for(Movie m:l.getList()) {
			Map<String,Object> m1 = new HashMap<>();
			Utils.transformBeanToMap(m, m1);
			list.add(m1);
		}
		int total = msi.findTotal();
		if(total % 5 == 0) {
			total /= 5;
		}else {
			total = (total / 5) + 1;
		}
		model.addObject("movieList",list);
		model.addObject("total",total);
		return model;
	}
	
	@RequestMapping("getAllMovieBypage")
	@ResponseBody
	public List<Map<String,Object>> getAllMovieBypage(ModelAndView model,@RequestParam(defaultValue="1") int current) throws IllegalArgumentException, IllegalAccessException {
		PageInfo<Movie> l = msi.findAllMoive(current);
		
		List<Map<String,Object>> list = new ArrayList<>();
		for(Movie m:l.getList()) {
			Map<String,Object> m1 = new HashMap<>();
			String type = "";
			Utils.transformBeanToMap(m, m1);
			list.add(m1);
		}
		return list;
	}
	
	@RequestMapping("addMovie")
	public String addMovie() {
		return "manage/addMovie";
	}
	
	
	@RequestMapping("getMovieDetail")
	@ResponseBody
	public Map<String,Object> getMovieDetail(Model model,int movieId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String, Object> map = msi.findMovieDetailsByMovieId(movieId);
		return map;
	}
}
