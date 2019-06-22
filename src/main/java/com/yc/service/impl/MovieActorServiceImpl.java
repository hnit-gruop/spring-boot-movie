package com.yc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Actor;
import com.yc.bean.MovieActor;
import com.yc.bean.MovieActorExample;
import com.yc.dao.MovieActorMapper;
import com.yc.service.MovieActorService;

@Service
public class MovieActorServiceImpl implements MovieActorService{
	@Resource
	MovieActorMapper mam;

	@Override
	public List<MovieActor> getActorByMovieId(int movieId) {
		MovieActorExample mae = new MovieActorExample();
		mae.createCriteria().andMovieIdEqualTo(movieId);
		List<MovieActor> selectByExample = mam.selectByExample(mae);
		return selectByExample;
	}

}