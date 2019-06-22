package com.yc.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yc.bean.Movie;

public interface MovieService {
	PageInfo<Movie> findAllMoive(int pageNum);
	
	Map<String,Object> findMovieDetailsByMovieId(int movieId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
	
	int findTotal();
	//数据库固定字段
	/**
	 * 上映中 
	 */
	String showing_status = "showing";
	/**
	 * 即将上映
	 */
	String up_coming_status = "upcoming";
	/**
	 * 经典电影
	 */
	String typical_status = "typical";
	
	
	/**
	 * 正在热映
	 */
	List<Movie> listShowing();
	/**
	 * 即将上映
	 */
	List<Movie> listUpComing();
	
	/**
	 * 最受期待
	 */
	List<Movie> mostExpect();
	
	/**
	 * 榜单
	 */
	List<Movie> top10();
	
	
	/**
	 * 设置封面
	 * @param list
	 */
	 void setCover(List<Movie> list);

	 void setCover(Movie movie);
	 
	 /**
	  * 设置评分
	  * @param movie
	  */
	 void setScore(Movie movie);
	 
	 void setScore(List<Movie> list);
	 
	 Movie get(int id);
	 
	 int upComingCnt();
	 
	 int showingCnt();
}
