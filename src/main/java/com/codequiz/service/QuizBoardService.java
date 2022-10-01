package com.codequiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.QuizBoardDao;
import com.codequiz.vo.QuizBoardPagingVo;
import com.codequiz.vo.QuizBoardVo;
import com.codequiz.vo.QuizCorrectCountVo;

@Component
public class QuizBoardService{
	@Autowired
	private QuizBoardDao dao;
	// 한 페이지 당 출력할 게시물의 개수.
	private static final int COUNT_PER_PAGE = 10;

	public void setDao(QuizBoardDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 퀴즈를 신규 등록함.
	 * @param vo 퀴즈의 모든 정보를 담은 객체.
	 * @return 성공 시 1, 실패 시 그 외의 값을 전달함.
	 */
	public int insertQuiz(QuizBoardVo vo) {
		return dao.insertQuiz(vo);
	}
	
	/**
	 * 해당 퀴즈를 맞춘 모든 사람 수 불러오기.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 해당 퀴즈를 맞춘 모든 사람 수
	 */
	public int selectQuizCorrectCount(int quizNum) {
		return dao.selectQuizCorrectCount(quizNum);
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
	 * 모든 퀴즈 리스트 불러오기.
	 * @return QuizBoardVo로 이루어진, 모든 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizAll() {
		return dao.selectQuizAll();
	}

	/**
	 * 한 페이지의 퀴즈 리스트 불러오기.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizList(int startRow, int countPerPage) {
		return dao.selectQuizList(startRow, countPerPage);
	}
	
	/**
	 * 카테고리로 분류된 한 페이지의 퀴즈 리스트 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizListByCategory(String category, int startRow, int countPerPage) {
		return dao.selectQuizListByCategory(category, startRow, countPerPage);
	}
	
	/**
	 * 마지막 등록한 퀴즈 3개 리스트 불러오기 
	 * @return 3개의 QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizLastThree() {
		return dao.selectQuizLastThree();
	}

	/**
	 * 하나의 퀴즈만 불러오기.
	 * @param quizNum 해당 퀴즈의 고유 번호.
	 * @return QuizBoardVo에 담긴 해당 퀴즈의 모든 정보.
	 */
	public QuizBoardVo selectQuiz(int quizNum) {
		return dao.selectQuiz(quizNum);
	}
	
	/**
	 * 퀴즈 제목으로 검색해서 퀴즈 리스트 불러오기.
	 * @param keyword 검색어
	 * @return 해당하는 검색어가 포함된 QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> searchQuiz(String keyword) {
		return dao.searchQuiz(keyword);
	}
	
	/*
	 * 모든 퀴즈의 개수 불러오기.
	 */
	public int selectQuizCount() {
		return dao.selectQuizCount();
	}

	/**
	 * 카테고리로 분류된 모든 퀴즈의 개수 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @return 카테고리로 분류된 모든 퀴즈의 개수.
	 */
	public int selectQuizCountByCategory(String category) {
		return dao.selectQuizCountByCategory(category);
	}
	
	/**
	 * 특정 아이디로 특정 퀴즈를 맞춘 이력이 있는 지 확인.
	 * @param memberId 회원 고유 번호.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 퀴즈 맞춘 이력이 있을 경우 1, 아닐 경우 0.
	 */
	public int selectQuizCorrectHistory(String memberId, int quizNum) {
		return dao.selectQuizCorrectHistory(memberId, quizNum);
	}
	
	/**
	 * 특정 퀴즈의 조회수를 가져옴.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 해당 퀴즈 조회수
	 */
	public int selectQuizView(int quizNum) {
		return dao.selectQuizView(quizNum);
	}
	
	/**
	 * 퀴즈 수정하기.
	 * @param vo 수정하여 변경된 퀴즈의 정보를 담은 QuizBoardVo.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int updateQuiz(QuizBoardVo vo) {
		return dao.updateQuiz(vo);
	}
	
	/**
	 * 퀴즈 조회수 증가.
	 * @param quizNum 퀴즈 고유 번호
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int updateQuizView(int quizView) {
		return dao.updateQuizView(quizView);
	}

	/**
	 * 퀴즈 삭제하기.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int deleteQuiz(int quizNum) {
		return dao.deleteQuiz(quizNum);
	}
	
	/**
	 * 맞춘 퀴즈 이력 삭제.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int deleteQuizCorrectCount(int quizNum) {
		return dao.deleteQuizCorrectCount(quizNum);
	}
	
	/**
	 * 게시판의 페이징을 위한 메소드.
	 * @param currentPage 현재 페이지
	 * @param category 카테고리
	 * @return 게시판의 퀴즈 목록, 현재 페이지, 시작 페이지, 끝 페이지, 전체 페이지, 각 퀴즈별 맞춘 사람 수 목록을 BoardPagingVo에 담아 반환.
	 */
	public QuizBoardPagingVo boardPaging(int currentPage, String category) {
		// 전체 게시물의 개수 구하기
		int totalCount = 0;
		
		// 카테고리 ALL 일경우 모든 게시물, 아닐 경우 해당 카테고리의 게시물 개수 구함
		if (category.equals("ALL")) {
			totalCount = dao.selectQuizCount();
		} else {
			totalCount = dao.selectQuizCountByCategory(category);
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
			boardList = dao.selectQuizList(startRow, COUNT_PER_PAGE);
		} else {
			boardList = dao.selectQuizListByCategory(category, startRow, COUNT_PER_PAGE);
		}
		
		// 문제별 맞춘 사람 수 조회
		List<Integer> correctMemberCount = new ArrayList<Integer>();
		for(QuizBoardVo vo : boardList) {
			correctMemberCount.add(dao.selectQuizCorrectCount(vo.getQuizNum()));
		}
		
		// 모든 데이터 객체에 담아 반환
		return new QuizBoardPagingVo(boardList, currentPage, startPage, endPage, totalPage, correctMemberCount);
	}
	
	/**
	 * 해당 회원이 전체 퀴즈 중에 어느정도나 퀴즈를 맞췄는지 퍼센테이지로 보여주는 메소드.
	 * @param memberId 회원 고유 번호.
	 * @return 맞춘 퀴즈 퍼센테이지.
	 */
	public double selectQuizCorrectPercent(String memberId) {
		double result = 0.0;
		// 멤버 아이디로 맞춘 문제 개수와 총 문제 개수 구해서 퍼센테이지 반환. 두 값중 하나라도 0이면 0.0 으로 표현.
		if(dao.selectQuizCount() > 0 && dao.selectMemberCorrectCount(memberId) > 0 ) {
			double percent = (double)selectMemberCorrectCount(memberId)/selectQuizCount();
			String stringResult = String.format("%.1f", percent*100);
			result = Double.parseDouble(stringResult);
		}
		return result;
	}
	
	/**
	 * 회원이 퀴즈의 답을 입력 했을 때, 해당 퀴즈의 정답 일치 여부와 중복을 확인 후 DB에 입력함. 
	 * @return 성공 시 1, 실패 시 0을 반환.
	 */
	public int insertQuizAnswer(QuizCorrectCountVo vo, int selectedQuizAnswer) {
		int result = 0;
		int quizNum = vo.getQuizNum();
		if(dao.isAnswerCorrect(quizNum, selectedQuizAnswer) == 1 && dao.selectQuizCorrectHistory(vo.getMemberId(), quizNum) == 0) {
			result = 1;
			dao.insertQuizCorrectCount(vo);
		} else if (dao.isAnswerCorrect(quizNum, selectedQuizAnswer) >= 1) {
			result = 1;
		}
		return result;
	}
}
