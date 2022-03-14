package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.MemberDAOImpl;
import com.itwillbs.domain.MemberDTO;


@Service
public class MemberServiceImpl implements MemberService{
	//
	@Inject
	private MemberDAO memberDAO;
	
	@Inject
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		// 처리작업
		//날짜처리
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("MemberServiceImpl insertmember()");
		//1방법 : MemberDAOImpl 객체생성 insertMember(memberDTO)메서드 호출
//		MemberDAOImpl memberDAO= new MemberDAOImpl();
//		memberDAO.insertMember(memberDTO);
		
		//2번째 방식 :부모인터페이스를 만들어서 => 자식클래서 객체 생성
		//          부모인터페이스.부모공통메서드호출()=> 부모인터페이스의 소문자 메서드(약속)
//		MemberDAO memberDAO = new MemberDAOImpl();
//		memberDAO.insertMember(memberDTO);
		
		//3번재 방법 자식클래스 스프링에서 객체생성한 찾고갑을 생성자 또는 set받아서 객체성성 메서드 호출
		memberDAO.insertMember(memberDTO);
	}

	

	
}
