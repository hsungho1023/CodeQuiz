package com.codequiz.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.mapper.MyPageMapper;
import com.codequiz.vo.QuizBoardVo;

@Component
public class MyPageDao{
	@Autowired
	private SqlSessionTemplate session;

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}
	
	/**
	 * 해당 퀴즈를 맞춘 모든 사람 수 불러오기.
	 * @param quizNum 퀴즈 고유 번호.
	 * @return 해당 퀴즈를 맞춘 모든 사람 수.
	 */
	public int selectQuizCorrectCount(int quizNum) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectQuizCorrectCount(quizNum);
	}
	
	/**
	 * 개인이 맞춘 문제의 수를 불러오기.
	 * @param memberId 회원 고유 번호.
	 * @return 해당 회원이 맞춘 모든 퀴즈의 수.
	 */
	public int selectMemberCorrectCount(String memberId) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectMemberCorrectCount(memberId);
	}
	
	/**
	 * 한 페이지의 퀴즈 리스트 불러오기.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @param memberId 현재 로그인한 아이디.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizList(int startRow, int countPerPage, String memberId) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectQuizList(startRow, countPerPage, memberId);
	}
	
	/**
	 * 카테고리로 분류된 한 페이지의 퀴즈 리스트 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @param startRow 해당 페이지의 맨 처음 게시물 번호.
	 * @param countPerPage 해당 페이지의 맨 마지막 게시물 번호.
	 * @param memberId 현재 로그인한 아이디.
	 * @return QuizBoardVo로 이루어진, 해당 페이지에 출력할 퀴즈가 담긴 List.
	 */
	public List<QuizBoardVo> selectQuizListByCategory(String category, int startRow, int countPerPage, String memberId) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectQuizListByCategory(category, startRow, countPerPage, memberId);
	}

	/*
	 * 모든 퀴즈의 개수 불러오기.
	 */
	public int selectQuizCount(String memberId) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectQuizCount(memberId);
	}
	
	/**
	 * 카테고리로 분류된 모든 퀴즈의 개수 불러오기.
	 * @param category 카테고리. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @return 카테고리로 분류된 모든 퀴즈의 개수.
	 */
	public int selectQuizCountByCategory(String category, String memberId) {
		MyPageMapper mapper = session.getMapper(MyPageMapper.class);
		return mapper.selectQuizCountByCategory(category, memberId);
	}
}
