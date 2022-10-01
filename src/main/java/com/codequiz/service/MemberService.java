package com.codequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.codequiz.repository.MemberDao;

import com.codequiz.vo.MemberVo;

@Component
public class MemberService {

	@Autowired
	private MemberDao dao;

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	// 회원가입 메소드
	public boolean join(MemberVo vo) throws DuplicateKeyException {

		try {
			if (dao.insertMember(vo) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} finally {
			System.out.println("에러");
		}
		return false;

	}

	// 로그인 메서드
	public boolean login(String memberId, String memberPassword) {
		System.out.println(dao.selectMemberNum(memberId, memberPassword));
		if (dao.selectMemberNum(memberId, memberPassword) == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 회원가입 중복
	public int isMemberId(String memberId) {
		return dao.isMemberId(memberId);
	}
}
