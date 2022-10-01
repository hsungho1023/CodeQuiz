package com.codequiz.vo;

import java.util.Date;

public class QnABoardVo {

	private int qnaNum;
	private String qnaTitle;
	private String qnaContent;
	private String qnaWriter;
	private Date qnaWriteDate;
	private int qnaReadCount;

	public QnABoardVo() {
		super();
	}

	public QnABoardVo(String qnaTitle, String qnaContent, String qnaWriter, Date qnaWriteDate, int qnaReadCount) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaWriter = qnaWriter;
		this.qnaWriteDate = qnaWriteDate;
		this.qnaReadCount = qnaReadCount;
	}

	public QnABoardVo(int qnaNum, String qnaTitle, String qnaContent, String qnaWriter, Date qnaWriteDate,
			int qnaReadCount) {
		super();
		this.qnaNum = qnaNum;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaWriter = qnaWriter;
		this.qnaWriteDate = qnaWriteDate;
		this.qnaReadCount = qnaReadCount;
	}

	public int getQnaNum() {
		return qnaNum;
	}

	public void setQnaNum(int qnaNum) {
		this.qnaNum = qnaNum;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public Date getQnaWriteDate() {
		return qnaWriteDate;
	}

	public void setQnaWriteDate(Date qnaWriteDate) {
		this.qnaWriteDate = qnaWriteDate;
	}

	public int getQnaReadCount() {
		return qnaReadCount;
	}

	public void setQnaReadCount(int qnaReadCount) {
		this.qnaReadCount = qnaReadCount;
	}

}