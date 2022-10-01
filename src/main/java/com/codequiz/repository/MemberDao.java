package com.codequiz.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.mapper.MemberMapper;

import com.codequiz.vo.MemberVo;

@Component
public class MemberDao {

	@Autowired
	private SqlSessionTemplate session;

	public int insertMember(MemberVo vo) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.insertMember(vo);
	}

	public int selectMemberNum(String memberId, String memberPassword) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.selectMemberNum(memberId, memberPassword);
	}

	public int isMemberId(String memberId) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		return mapper.isMemberId(memberId);
	}

}
