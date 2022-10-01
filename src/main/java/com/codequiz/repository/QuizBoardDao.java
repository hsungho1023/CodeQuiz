package com.codequiz.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.mapper.QuizBoardMapper;
import com.codequiz.vo.QuizBoardVo;
import com.codequiz.vo.QuizCorrectCountVo;

@Component
public class QuizBoardDao{
	@Autowired
	private SqlSessionTemplate session;

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}

	/**
	 * 퀴즈를 신규 등록함.
	 * @param vo 퀴즈의 모든 정보를 담은 객체.
	 * @return 성공 시 1, 실패 시 그 외의 값을 전달함.
	 */
	public int insertQuiz(QuizBoardVo vo) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.insertQuiz(vo);
	}
	
	/**
	 * 회원이 맞춘 퀴즈를 기록함.
	 * @param vo 회원 고유 번호, 퀴즈 고유 번호를 담은 객체.
	 * @return 성공 시 1, 실패 시 그 외의 값을 전달함.
	 */
	public int insertQuizCorrectCount(QuizCorrectCountVo vo) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.insertQuizCorrectCount(vo);
	}

	/**
	 * 해당 퀴즈를 맞춘 모든 사람 수 불러오기.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 해당 퀴즈를 맞춘 모든 사람 수.
	 */
	public int selectQuizCorrectCount(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizCorrectCount(quizNum);
	}
	
	/**
	 * 개인이 맞춘 문제의 수를 불러오기.
	 * @param memberId 회원 고유 번호.
	 * @return 해당 회원이 맞춘 모든 퀴즈의 수.
	 */
	public int selectMemberCorrectCount(String memberId) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectMemberCorrectCount(memberId);
	}
	
	/**
	 * 모든 퀴즈 리스트 불러오기.
	 * @return QuizBoardVo로 이루어진, 모든 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizAll() {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizAll();
	}
	
	/**
	 * 한 페이지의 퀴즈 리스트 불러오기.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizList(int startRow, int countPerPage) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizList(startRow, countPerPage);
	}
	
	/**
	 * 카테고리로 분류된 한 페이지의 퀴즈 리스트 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizListByCategory(String category, int startRow, int countPerPage) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizListByCategory(category, startRow, countPerPage);
	}

	/**
	 * 마지막 등록한 퀴즈 3개 리스트 불러오기 
	 * @return 3개의 QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizLastThree() {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizLastThree();
	}

	/**
	 * 하나의 퀴즈만 불러오기.
	 * @param quizNum 해당 퀴즈의 고유 번호.
	 * @return QuizBoardVo에 담긴 해당 퀴즈의 모든 정보.
	 */
	public QuizBoardVo selectQuiz(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuiz(quizNum);
	}
	
	/**
	 * 퀴즈 제목으로 검색해서 퀴즈 리스트 불러오기.
	 * @param keyword 검색어
	 * @return 해당하는 검색어가 포함된 QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> searchQuiz(String keyword) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.searchQuiz(keyword);
	}
	
	/*
	 * 모든 퀴즈의 개수 불러오기.
	 */
	public int selectQuizCount() {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizCount();
	}
	
	/**
	 * 카테고리로 분류된 모든 퀴즈의 개수 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @return 카테고리로 분류된 모든 퀴즈의 개수.
	 */
	public int selectQuizCountByCategory(String category) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizCountByCategory(category);
	}
	
	/**
	 * 특정 아이디로 특정 퀴즈를 맞춘 이력이 있는 지 확인.
	 * @param memberId 회원 고유 번호.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 퀴즈 맞춘 이력이 있을 경우 1, 아닐 경우 0.
	 */
	public int selectQuizCorrectHistory(String memberId, int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizCorrectHistory(memberId, quizNum);
	}
	
	/**
	 * 특정 퀴즈의 조회수를 가져옴.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 해당 퀴즈 조회수
	 */
	public int selectQuizView(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.selectQuizView(quizNum);
	}
	
	/**
	 * 회원이 입력한 답이 문제의 올바른 정답인지 확인.
	 * @param quizNum 퀴즈 고유 번호
	 * @param selectedQuizAnswer 회원이 입력한 답.
	 * @return 맞을 경우 1, 아닐 경우 0 반환.
	 */
	public int isAnswerCorrect(int quizNum, int selectedQuizAnswer) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.isAnswerCorrect(quizNum, selectedQuizAnswer);
	}
	
	/**
	 * 퀴즈 수정하기.
	 * @param vo 수정하여 변경된 퀴즈의 정보를 담은 QuizBoardVo.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int updateQuiz(QuizBoardVo vo) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.updateQuiz(vo);
	}
	
	/**
	 * 퀴즈 조회수 증가.
	 * @param quizNum 퀴즈 고유 번호
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int updateQuizView(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.updateQuizView(quizNum);
	}

	/**
	 * 퀴즈 삭제하기.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int deleteQuiz(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.deleteQuiz(quizNum);
	}
	
	/**
	 * 맞춘 퀴즈 이력 삭제.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 성공 시 1, 실패 시 그외의 값을 반환함.
	 */
	public int deleteQuizCorrectCount(int quizNum) {
		QuizBoardMapper mapper = session.getMapper(QuizBoardMapper.class);
		return mapper.deleteQuizCorrectCount(quizNum);
	}
}
