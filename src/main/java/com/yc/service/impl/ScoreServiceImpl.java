package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.Score;
import com.yc.bean.ScoreExample;
import com.yc.dao.ScoreMapper;
import com.yc.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{
	
	@Autowired
	ScoreMapper scoreMapper;
	
	@Override
	public double get(int movieId) {
		ScoreExample example = new ScoreExample();
		example.createCriteria().andMovieIdEqualTo(movieId);
		List<Score> list = scoreMapper.selectByExample(example);
		if(list.size()>0)
			return list.get(0).getScore();
		return 0;
	}
}
