package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yc.bean.Movie;
import com.yc.bean.MovieExample;
import com.yc.dao.MovieMapper;
import com.yc.service.MovieImageServie;
import com.yc.service.MovieService;
import com.yc.service.ScoreService;

@Service
public class MovieServiceImpl implements MovieService{

	
	@Autowired
	MovieMapper movieMapper;
	
	@Autowired
	MovieImageServie movieImageServie;
	
	@Autowired
	ScoreService scoreService;
	
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
		String cover = movieImageServie.getCover(movie.getMovieId());
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
		double score = scoreService.get(movie.getMovieId());
		movie.setScore(score);
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
}
