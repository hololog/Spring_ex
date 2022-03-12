package com.itwillbs.service;

import com.itwillbs.domain.MemberDTO;

public interface MemberService {
	//부모인터페이스 : 자식클래스를 만들기 위한 공통된 틀(공통된 메서드 틀)
	//추상메서트 : 메서드 틀
	public void insertMember(MemberDTO memberDTO);
}
