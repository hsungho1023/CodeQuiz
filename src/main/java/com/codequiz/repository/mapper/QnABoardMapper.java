package com.codequiz.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codequiz.vo.QnABoardVo;

public interface QnABoardMapper {
	public int insertQnA(QnABoardVo qna);

	public int selectQnATotalCount();

	public QnABoardVo selectQnA(int qnaNum);

	public List<QnABoardVo> selectQnAList(@Param("startRow") int startRow, @Param("count") int count);

	public int updateQnA(QnABoardVo qna);

	public int deleteQnA(int qnaNum);

	public int updateReadCount(int qnaNum);
	
	public List<QnABoardVo> selectQnALastThree();
}
