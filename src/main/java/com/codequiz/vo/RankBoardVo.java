package com.codequiz.vo;

public class RankBoardVo {

	private String quizType;
	private String memberId;
	private int count;
	private String rank;

	public String getQuizType() {
		return quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public RankBoardVo(String quizType, String memberId, int count, String rank) {
		super();
		this.quizType = quizType;
		this.memberId = memberId;
		this.count = count;
		this.rank = rank;
	}

	public RankBoardVo(String quizType, String memberId, int count) {
		super();
		this.quizType = quizType;
		this.memberId = memberId;
		this.count = count;
	}

	public RankBoardVo() {

	}

}
