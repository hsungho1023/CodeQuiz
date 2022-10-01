package com.codequiz.vo;

public class QuizCorrectCountVo {
	private String memberId;
	private int quizNum;

	public QuizCorrectCountVo() {
		super();
	}

	public QuizCorrectCountVo(String memberId, int quizNum) {
		super();
		this.memberId = memberId;
		this.quizNum = quizNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(int quizNum) {
		this.quizNum = quizNum;
	}

}
