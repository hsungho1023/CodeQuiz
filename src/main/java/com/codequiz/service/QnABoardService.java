package com.codequiz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.QnABoardDao;

import com.codequiz.vo.QnAPagingVo;
import com.codequiz.vo.QnABoardVo;

@Component
public class QnABoardService {
	@Autowired
	public QnABoardDao dao;
	private static final int COUNT_PER_PAGE = 10;

	public QnAPagingVo makeQnAPage(int currentPage) {
		// 게시글 총 갯수
		int totalCount = dao.selectQnATotalCount();

		// 페이지 총 갯수
		int totalPage = totalCount / COUNT_PER_PAGE;

		if (totalCount % COUNT_PER_PAGE != 0) {
			totalPage++;
		}

		int startPage = (currentPage - 1) / 10 * 10 + 1;

		int endPage = startPage + 9;
		if (totalPage < endPage) {
			endPage = totalPage;
		}

		int startRow = (currentPage - 1) * COUNT_PER_PAGE;

		List<QnABoardVo> qnaList = dao.selectQnAList(startRow, COUNT_PER_PAGE);

		return new QnAPagingVo(qnaList, currentPage, startPage, endPage, totalPage);
	}

	public int insertQnA(QnABoardVo qna, String userId) {
		qna.setQnaReadCount(0); // 조회수
		qna.setQnaWriteDate(new Date()); // 작성일자
		qna.setQnaWriter(userId); // 작성자

		return dao.insertQnA(qna);
	}

	public QnABoardVo selectQnA(int qnaNum, String userId) {
		QnABoardVo qna = dao.selectQnA(qnaNum);
		if (userId != null && userId.equals(qna.getQnaWriter())) {
			return qna;
		} else {
			dao.updateReadCount(qnaNum);
			return dao.selectQnA(qnaNum);
		}
	}

	public QnABoardVo readNoCount(int qnaNum) {
		return dao.selectQnA(qnaNum);
	}

	public boolean updateQnA(QnABoardVo qna, String userId) {
		QnABoardVo real = dao.selectQnA(qna.getQnaNum());

		if (userId != null && userId.equals(real.getQnaWriter())) {
			qna.setQnaWriteDate(new Date());
			dao.updateQnA(qna);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteQnA(int qnaNum, String userId) {
		QnABoardVo qna = dao.selectQnA(qnaNum);

		if (userId != null && userId.equals(qna.getQnaWriter())) {
			dao.deleteQnA(qnaNum);
			return true;
		} else {
			return false;
		}
	}
	
	public List<QnABoardVo> selectQnALastThree() {
		return dao.selectQnALastThree();
	}
}
