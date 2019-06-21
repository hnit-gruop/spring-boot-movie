package com.yc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.bean.Movie;
import com.yc.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	MovieService movieService;
	/**
	 * 首页
	 */
	@RequestMapping(value = { "/", "index" })
	public String index(Model m) {
		List<Movie> listShowing = movieService.listShowing();
		List<Movie> listUpComing = movieService.listUpComing();
		m.addAttribute("listShowing", listShowing);
		m.addAttribute("listUpComing", listUpComing);
		m.addAttribute("cnt1",movieService.showingCnt());
		m.addAttribute("cnt2",movieService.upComingCnt());
		m.addAttribute("index", 1);
		return "pages/HomePage";
	}
	
	/**
	 * 电影详情
	 */
	@RequestMapping("movieDetail")
	public String movieDetail(Model m,@RequestParam(required=true)int id) {
		Movie movie = movieService.get(id);
		m.addAttribute("movie", movie);
		return "pages/MovieDetail";
	}

}
