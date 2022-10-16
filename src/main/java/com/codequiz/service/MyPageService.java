package com.codequiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.MyPageDao;
import com.codequiz.vo.QuizBoardPagingVo;
import com.codequiz.vo.QuizBoardVo;

@Component
public class MyPageService{
	@Autowired
	private MyPageDao dao;
	// 한 페이지 당 출력할 게시물의 개수.
	private static final int COUNT_PER_PAGE = 10;

	public void setDao(MyPageDao dao) {
		this.dao = dao;
	}

	/**
	 * 개인이 맞춘 문제의 수를 불러오기.
	 * @param memberId 회원 고유 번호.
	 * @return 해당 회원이 맞춘 모든 퀴즈의 수.
	 */
	public int selectMemberCorrectCount(String memberId) {
		return dao.selectMemberCorrectCount(memberId);
	}
	
	/**
	 * 게시판의 페이징을 위한 메소드.
	 * @param currentPage 현재 페이지
	 * @param category 카테고리
	 * @param memberId 현재 로그인한 아이디
	 * @return 게시판의 퀴즈 목록, 현재 페이지, 시작 페이지, 끝 페이지, 전체 페이지, 각 퀴즈별 맞춘 사람 수 목록을 BoardPagingVo에 담아 반환.
	 */
	public QuizBoardPagingVo boardPaging(int currentPage, String category, String memberId) {
		// 전체 게시물의 개수 구하기
		int totalCount = 0;
		
		// 카테고리 ALL 일 경우 맞춘 모든 게시물, 아닐 경우 해당 카테고리의 맞춘 게시물 개수 구함
		if (category.equals("ALL")) {
			totalCount = dao.selectQuizCount(memberId);
		} else {
			totalCount = dao.selectQuizCountByCategory(category, memberId);
		}
		
		// 총 페이지 개수 구하기
		int totalPage = totalCount/COUNT_PER_PAGE;
		if(totalCount % COUNT_PER_PAGE != 0) {
			totalPage++;
		}
		
		// 하단 페이지네이션
		// ex) 현재 페이지 23 -1
		//				 => 22 /10
		//			 	 => 2 *10
		//			 	 => 20 +1
		//	   시작 페이지 21
		int startPage = (currentPage-1)/5*5+1;
		int endPage = startPage + 4;
		if (totalPage < endPage) {
			endPage = totalPage;
		}

		// 글 조회 행 시작 번호 계산
		// 페이지 당 0~9 행만 출력
		int startRow = (currentPage-1) * COUNT_PER_PAGE;
		
		// DB에서 보여질 게시글 목록 조회
		List<QuizBoardVo> boardList;
		if (category.equals("ALL")) {
			boardList = dao.selectQuizList(startRow, COUNT_PER_PAGE, memberId);
		} else {
			boardList = dao.selectQuizListByCategory(category, startRow, COUNT_PER_PAGE, memberId);
		}
		
		// 문제별 맞춘 사람 수 조회
		List<Integer> correctMemberCount = new ArrayList<Integer>();
		for(QuizBoardVo vo : boardList) {
			correctMemberCount.add(dao.selectQuizCorrectCount(vo.getQuizNum()));
		}
		
		// 모든 데이터 객체에 담아 반환
		return new QuizBoardPagingVo(boardList, currentPage, startPage, endPage, totalPage, correctMemberCount);
	}
}
