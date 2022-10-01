package com.codequiz.repository.mapper;

import com.codequiz.vo.QuizBoardVo;
import com.codequiz.vo.QuizCorrectCountVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface QuizBoardMapper {
	public int insertQuiz(QuizBoardVo vo);

	public int insertQuizCorrectCount(QuizCorrectCountVo vo);

	public int selectQuizCorrectCount(int quizNum);

	public int selectMemberCorrectCount(String memberId);

	public List<QuizBoardVo> selectQuizAll();

	public List<QuizBoardVo> selectQuizList(@Param("startRow") int startRow,
			@Param("count_per_page") int count_per_page);

	public List<QuizBoardVo> selectQuizListByCategory(@Param("category") String category,
			@Param("startRow") int startRow, @Param("count_per_page") int count_per_page);

	public List<QuizBoardVo> selectQuizLastThree();

	public QuizBoardVo selectQuiz(int quizNum);

	public List<QuizBoardVo> searchQuiz(String keyword);

	public int selectQuizCount();

	public int selectQuizCountByCategory(@Param("category") String category);

	public int selectQuizCorrectHistory(String memberId, int quizNum);

	public int selectQuizView(int quizNum);

	public int isAnswerCorrect(int quizNum, int selectedQuizAnswer);

	public int updateQuiz(QuizBoardVo vo);

	public int updateQuizView(int quizNum);

	public int deleteQuiz(int quizNum);

	public int deleteQuizCorrectCount(int quizNum);
}
