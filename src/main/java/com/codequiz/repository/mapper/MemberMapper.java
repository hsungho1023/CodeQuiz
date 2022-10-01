package com.codequiz.repository.mapper;

import com.codequiz.vo.MemberVo;

public interface MemberMapper {
	public int insertMember(MemberVo member);

	public int selectMemberNum(String memberId, String memberPassword);

	public int isMemberId(String memberId);
}
