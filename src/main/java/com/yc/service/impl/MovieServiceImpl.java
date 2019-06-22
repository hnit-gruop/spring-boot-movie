package com.yc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.Movie;
import com.yc.bean.MovieActor;
import com.yc.bean.MovieExample;
import com.yc.bean.MovieImage;
import com.yc.bean.MovieType;
import com.yc.bean.Type;
import com.yc.dao.MovieMapper;
import com.yc.service.MovieActorService;
import com.yc.service.MovieImageService;
import com.yc.service.MovieService;
import com.yc.service.RedisService;
import com.yc.service.ScoreService;
import com.yc.util.Utils;

@Service
public class MovieServiceImpl implements MovieService{

	
	@Autowired
	MovieMapper movieMapper;
	
	@Autowired
	MovieImageService movieImageService;
	
	@Autowired
	ScoreService scoreService;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Resource
	MovieActorService movieActorService;
	@Autowired
	RedisService redisServie;
	
	@Override
	public List<Movie> listShowing() {
		MovieExample e = new MovieExample();
		e.createCriteria().andStatusEqualTo(MovieService.showing_status);
		PageHelper.startPage(1, 8);
		List<Movie> list = movieMapper.selectByExample(e);
		setCover(list);
		setScore(list);
		return list;
	}

	@Override
	public List<Movie> listUpComing() {
		MovieExample e = new MovieExample();
		e.createCriteria().andStatusEqualTo(MovieService.up_coming_status);
		PageHelper.startPage(1, 8);
		List<Movie> list = movieMapper.selectByExample(e);
		//设置封面
		setCover(list);
		setScore(list);
		return list;
	}

	@Override
	public List<Movie> mostExpect() {
		return null;
	}

	@Override
	public List<Movie> top10() {
		return null;
	}

	@Override
	public void setCover(List<Movie> list) {
		for (Movie movie : list) {
			setCover(movie);
		}
	}

	@Override
	public void setCover(Movie movie) {
		String cover = movieImageService.getCover(movie.getMovieId());
		movie.setCoverImage(cover);
	}

	@Override
	public Movie get(int id) {
		Movie m = movieMapper.selectByPrimaryKey(id);
		setCover(m);
		return m;
	}

	@Override
	public void setScore(Movie movie) {
		
//		// 在数据库中取值 
//		double score = scoreService.get(movie.getMovieId()); 

		
		//在redis中取值
		double avgScore = redisServie.getAvgScore(movie.getMovieId());
		movie.setScore(avgScore);
	}

	@Override
	public void setScore(List<Movie> list) {
		for (Movie movie : list) {
			setScore(movie);
		}
	}

	@Override
	public int upComingCnt() {
		MovieExample e = new MovieExample();
		e.createCriteria().andStatusEqualTo(MovieService.up_coming_status);
		long cnt = movieMapper.countByExample(e);
		return (int)cnt;
	}
	
	@Override
	public int showingCnt() {
		MovieExample e = new MovieExample();
		e.createCriteria().andStatusEqualTo(MovieService.showing_status);
		long cnt = movieMapper.countByExample(e);
		return (int)cnt;
	}

	
	@Override
	public PageInfo<Movie> findAllMoive(int pageNum) {
		PageHelper.startPage(pageNum,5);
		List<Movie> lm = movieMapper.selectByExample(null);
		PageInfo<Movie> list = new PageInfo<>(lm);
		return list;
	}

	
	
	@Override
	public Map<String, Object> findMovieDetailsByMovieId(int movieId)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Map<String,Object> map = new HashMap<>();
		Movie movie = movieMapper.selectByPrimaryKey(movieId);
		Utils.transformBeanToMap(movie, map);
		List<MovieActor> actorByMovieId = movieActorService.getActorByMovieId(movieId);
		List<MovieImage> movieImageByMovieId = movieImageService.getMovieImageByMovieId(movieId);
		map.put("actorList", actorByMovieId);
		map.put("imageList", movieImageByMovieId);
		return map;
	}

	@Override
	public int findTotal() {
		List<Movie> lm = movieMapper.selectByExample(null);
		return lm.size();
	}

}
