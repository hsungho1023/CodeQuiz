package com.codequiz.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.mapper.QnABoardMapper;

import com.codequiz.vo.QnABoardVo;

@Component
public class QnABoardDao {
	@Autowired
	private SqlSessionTemplate session;
	
	public int insertQnA(QnABoardVo qna) {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.insertQnA(qna);
	}
	
	
	public int selectQnATotalCount() {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.selectQnATotalCount();
	}
	
	
	public QnABoardVo selectQnA(int qnaNum) {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.selectQnA(qnaNum);
	}
	
	
	public List<QnABoardVo> selectQnAList(int startRow, int count){
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.selectQnAList(startRow, count);
	}
	
	
	public int updateReadCount(int qnaNum) {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.updateReadCount(qnaNum);
	}
	
	
	public int deleteQnA(int qnaNum) {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.deleteQnA(qnaNum);
	}
	
	
	public int updateQnA(QnABoardVo qna) {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.updateQnA(qna);
	}

	public List<QnABoardVo> selectQnALastThree() {
		QnABoardMapper mapper = session.getMapper(QnABoardMapper.class);
		return mapper.selectQnALastThree();
	}
}
