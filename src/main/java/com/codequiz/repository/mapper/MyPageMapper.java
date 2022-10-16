package com.codequiz.repository.mapper;

import com.codequiz.vo.QuizBoardVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MyPageMapper {
	public int selectQuizCorrectCount(int quizNum);

	public int selectMemberCorrectCount(String memberId);

	public List<QuizBoardVo> selectQuizList(
			@Param("startRow") int startRow,
			@Param("count_per_page") int count_per_page, 
			@Param("memberId") String memberId);

	public List<QuizBoardVo> selectQuizListByCategory(
			@Param("category") String category,
			@Param("startRow") int startRow, 
			@Param("count_per_page") int count_per_page, 
			@Param("memberId") String memberId);

	public int selectQuizCount(@Param("memberId") String memberId);

	public int selectQuizCountByCategory(@Param("category") String category, @Param("memberId") String memberId);
}
