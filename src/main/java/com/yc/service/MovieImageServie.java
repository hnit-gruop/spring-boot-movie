package com.yc.service;

import com.yc.bean.MovieImage;

public interface MovieImageServie {
	String COVER_TYPE = "cover_type";//封面图片
	String BIG_IMG_TYPE = "big_img_type";//剧情大图
	String SMALL_IMG_TYPE = "small_img_type";//剧情小图
	
	String getCover(int movieId);
	String getBig(int movieId);
	String getSmall(int movieId);
	
	int add(MovieImage mi);
	int delete(MovieImage mi);
	int update(MovieImage mi);
}
