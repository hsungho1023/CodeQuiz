package com.codequiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.QuizBoardDao;
import com.codequiz.repository.RankBoardDao;

import com.codequiz.vo.RankBoardVo;

@Component
public class RankBoardService {
	@Autowired
	QuizBoardService service;

	@Autowired
	RankBoardDao dao;

	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectJavaRank() {
		List<RankBoardVo> list = dao.selectJavaRank();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			resultVo.setMemberId(vo.getMemberId());
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}

	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectSqlRank() {
		List<RankBoardVo> list = dao.selectSqlRank();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			resultVo.setMemberId(vo.getMemberId());
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}

	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectHtmlRank() {
		List<RankBoardVo> list = dao.selectHtmlRank();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			resultVo.setMemberId(vo.getMemberId());
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}

	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectCssRank() {
		List<RankBoardVo> list = dao.selectCssRank();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			resultVo.setMemberId(vo.getMemberId());
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}
	
	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectJavaRankTopThree() {
		List<RankBoardVo> list = dao.selectJavaRankTopThree();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			if (vo.getMemberId().length() >= 6) {
				resultVo.setMemberId(vo.getMemberId().substring(0,6) + "..");
			} else {
				resultVo.setMemberId(vo.getMemberId());
			}
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}
	
	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectSqlRankTopThree() {
		List<RankBoardVo> list = dao.selectSqlRankTopThree();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			if (vo.getMemberId().length() >= 6) {
				resultVo.setMemberId(vo.getMemberId().substring(0,6) + "..");
			} else {
				resultVo.setMemberId(vo.getMemberId());
			}
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}
	
	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectHtmlRankTopThree() {
		List<RankBoardVo> list = dao.selectHtmlRankTopThree();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			if (vo.getMemberId().length() >= 6) {
				resultVo.setMemberId(vo.getMemberId().substring(0,6) + "..");
			} else {
				resultVo.setMemberId(vo.getMemberId());
			}
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}
	
	// 랭킹 뿌려주긔
	public List<RankBoardVo> selectCssRankTopThree() {
		List<RankBoardVo> list = dao.selectCssRankTopThree();
		List<RankBoardVo> resultList = new ArrayList<RankBoardVo>();
		for (RankBoardVo vo : list) {
			RankBoardVo resultVo = new RankBoardVo();
			if (vo.getMemberId().length() >= 6) {
				resultVo.setMemberId(vo.getMemberId().substring(0,6) + "..");
			} else {
				resultVo.setMemberId(vo.getMemberId());
			}
			resultVo.setQuizType(vo.getQuizType());
			resultVo.setCount(vo.getCount());
			resultVo.setRank(rankIcon(vo.getMemberId()));
			resultList.add(resultVo);
		}
		return resultList;
	}

	// 계산해줘야돼 ................. ! ........ !
	// 해주래 !!!!!!!!!!!!!!!!!!!!!!! 더블로 받아야될거같은데
	public String rankIcon(String memberId) {
		double memberPercent = service.selectQuizCorrectPercent(memberId);
		if (memberPercent >= 0.0 && memberPercent <= 12.5) {
			return "Bronze";
		} else if (memberPercent >= 12.6 && memberPercent <= 25.0) {
			return "Silver";
		} else if (memberPercent >= 25.1 && memberPercent <= 37.5) {
			return "Gold";
		} else if (memberPercent >= 37.6 && memberPercent <= 50.0) {
			return "Platinum";
		} else if (memberPercent >= 50.1 && memberPercent <= 62.5) {
			return "Diamond";
		} else if (memberPercent >= 62.6 && memberPercent <= 75.0) {
			return "Master";
		} else if (memberPercent >= 75.1 && memberPercent <= 87.5) {
			return "GrandMaster";
		} else if (memberPercent >= 87.6 && memberPercent <= 100.0) {
			return "Challenger";
		} else {
			return "None";
		}
	}
}
