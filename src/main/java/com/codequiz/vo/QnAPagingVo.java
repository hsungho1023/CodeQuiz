package com.codequiz.vo;

import java.util.List;

public class QnAPagingVo {

	private List<QnABoardVo> qnaList;

	// 현재 페이지
	private int currentPage;

	// 시작 페이지
	private int startPage;

	// 마지막 페이지
	private int endPage;

	// 총 페이지
	private int totalPage;

	public QnAPagingVo(List<QnABoardVo> qnaList, int currentPage, int startPage, int endPage, int totalPage) {
		super();
		this.qnaList = qnaList;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
	}

	public List<QnABoardVo> getQnaList() {
		return qnaList;
	}

	public void setQnaList(List<QnABoardVo> qnaList) {
		this.qnaList = qnaList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "QnAPageVO [qnaList=" + qnaList + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", totalPage=" + totalPage + "]";
	}
}
