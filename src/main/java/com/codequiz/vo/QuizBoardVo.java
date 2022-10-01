package com.codequiz.vo;

import java.util.Date;

public class QuizBoardVo {
	private int quizNum;
	private String quizTitle;
	private String quizContents;
	private String quizWriter;
	private Date quizWriteDate;
	private String quizAnswer1;
	private String quizAnswer2;
	private String quizAnswer3;
	private String quizAnswer4;
	private int quizTrueAnswer;
	private int quizView;
	private String quizType;

	public QuizBoardVo() {
		super();
	}

	public QuizBoardVo(int quizNum, String quizTitle, String quizContents, String quizWriter, Date quizWriteDate,
			String quizAnswer1, String quizAnswer2, String quizAnswer3, String quizAnswer4, int quizTrueAnswer,
			int quizView, String quizType) {
		super();
		this.quizNum = quizNum;
		this.quizTitle = quizTitle;
		this.quizContents = quizContents;
		this.quizWriter = quizWriter;
		this.quizWriteDate = quizWriteDate;
		this.quizAnswer1 = quizAnswer1;
		this.quizAnswer2 = quizAnswer2;
		this.quizAnswer3 = quizAnswer3;
		this.quizAnswer4 = quizAnswer4;
		this.quizTrueAnswer = quizTrueAnswer;
		this.quizView = quizView;
		this.quizType = quizType;
	}

	public int getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(int quizNum) {
		this.quizNum = quizNum;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String getQuizContents() {
		return quizContents;
	}

	public void setQuizContents(String quizContents) {
		this.quizContents = quizContents;
	}

	public String getQuizWriter() {
		return quizWriter;
	}

	public void setQuizWriter(String quizWriter) {
		this.quizWriter = quizWriter;
	}

	public Date getQuizWriteDate() {
		return quizWriteDate;
	}

	public void setQuizWriteDate(Date quizWriteDate) {
		this.quizWriteDate = quizWriteDate;
	}

	public String getQuizAnswer1() {
		return quizAnswer1;
	}

	public void setQuizAnswer1(String quizAnswer1) {
		this.quizAnswer1 = quizAnswer1;
	}

	public String getQuizAnswer2() {
		return quizAnswer2;
	}

	public void setQuizAnswer2(String quizAnswer2) {
		this.quizAnswer2 = quizAnswer2;
	}

	public String getQuizAnswer3() {
		return quizAnswer3;
	}

	public void setQuizAnswer3(String quizAnswer3) {
		this.quizAnswer3 = quizAnswer3;
	}

	public String getQuizAnswer4() {
		return quizAnswer4;
	}

	public void setQuizAnswer4(String quizAnswer4) {
		this.quizAnswer4 = quizAnswer4;
	}

	public int getQuizTrueAnswer() {
		return quizTrueAnswer;
	}

	public void setQuizTrueAnswer(int quizTrueAnswer) {
		this.quizTrueAnswer = quizTrueAnswer;
	}

	public int getQuizView() {
		return quizView;
	}

	public void setQuizView(int quizView) {
		this.quizView = quizView;
	}

	public String getQuizType() {
		return quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}

}
