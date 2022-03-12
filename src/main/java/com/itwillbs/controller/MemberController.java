package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;

@Controller
public class MemberController {

//	MemberService memberService = new MemberServiceImpl(); //xml에서 객체생성해서 전달
	//부모인터페이스 변수를 멤버변수로 정의 set, 생성자 를 통해 값을 쉽게 전달받을수 있음)
	//DI의존관계(객체생성관계)주입 : 객체생성을 xml에서 수행하여 필요로 하는 자바파일에 주입
	@Inject
	private MemberService memberService;
	
	//생성자 방식(선택)
//	@Inject
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	//set메서드 방식(선택)
	@Inject
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
//	사용자 가상주소 http://localhost:8080/myweb/member/insert
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		System.out.println("MemberController insert() ");
		
		// 1. 하이퍼링크 2.location.href 3. response.sendRedirect()
        //		response.sendRedirect("member/insertForm.jsp");
		
		// 2. 가상주소 http://localhost:8080/myweb/member/insert 주소줄에 보이면서
		// 실행화면 member/insertForm.jsp 보이기
//		forward이동방식 => /member/insert 주소줄에 유지 실행화면 member/insertForm.jsp 보이기
//		RequestDispatcher dispatcher=request.getRequestDispatcher("member/insertForm.jsp");
//		dispatcher.forward(request, response);
		
	   //  /WEB-INF/views/(접두사)+member/insertForm + .jsp(접미사)
		// => /WEB-INF/views/member/insertForm.jsp 이동(주소줄에 주소가 안바뀌면서 이동)
		return "member/insertForm";//실제주소 jsp파일은 포워드방식 이동
	}
	

	//	사용자 가상주소 http://localhost:8080/myweb/member/insertPro
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		//insertPro(HttpServletRequest request)
		System.out.println("MemberController insertPro() ");
//		String id=request.getParameter("id");
//		String pass=request.getParameter("pass");
//		String name=request.getParameter("name");
//		
//		MemberDTO memberDTO=new MemberDTO();
//		memberDTO.setId(id);
//		memberDTO.setPass(pass);
//		memberDTO.setName(name);
		// insertForm.jsp에서 태그 name="id" 와  MemberDTO 멤버변수 id => 일치하면 자동으로 저장
		
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		//회원가입 메서드호출
		//페키지 com.itwillbs.service  MemberServiceImpl insertMember(memberDTO)호출
		//=>패키지 com.itwillbs.dao  MemberDAOImpl insertMember(memberDTO)호출
		
		//1번째 방식: MemberDAOImpl 객체생성 insertMember(memberDTO)메서드 호출
//		MemberServiceImpl memberService = new MemberServiceImpl();
//		memberService.insertMember(memberDTO);
		
		//2번째 방식 :부모인터페이스를 만들어서 => 자식클래서 객체 생성
		//          부모인터페이스.부모공통메서드호출()=> 부모인터페이스의 소문자 메서드(약속)
//		MemberService memberService = new MemberServiceImpl();
//		memberService.insertMember(memberDTO);
		
		//3번째 방식 :스프링에서 객체를 생성해서 객체 생성한 참조값을 생성자 또는 set메서드로 받아서 객체생성 메서드호출
//		MemberService memberService = new MemberServiceImpl(); //xml에서 객체생성해서 전달
		memberService.insertMember(memberDTO);
		
		// 가상주소 로그인주소 이동 /member/login (주소줄에 주소가 바뀌면서 이동)리다이렉트방식(jsp파일 이외)
		// 	response.sendRedirect("member/login");
		return "redirect:/member/login";
	}
	
//	사용자 가상주소 http://localhost:8080/myweb/member/login
	// System.out.println("HomeController login() ");
	// loginForm.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login() ");
		
		return "member/loginForm";
	}
	
	//	사용자 가상주소 http://localhost:8080/myweb/member/loginPro
	// System.out.println("HomeController loginPro() ");
	// 가상주소 로그인주소 이동 /member/main (주소줄에 주소가 바뀌면서 이동)
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO) {
		System.out.println("MemberController loginPro() ");
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		
		return "redirect:/member/main";
	}	
	
//	사용자 가상주소 http://localhost:8080/myweb/member/main
	// System.out.println("MemberController main() ");
	// member/main.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("MemberController main() ");
		
		return "member/main";
	}
	
//	사용자 가상주소 http://localhost:8080/myweb/member/info
	// System.out.println("MemberController info() ");
	// member/info.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info() {
		System.out.println("MemberController info() ");
		
		return "member/info";
	}
	
	
//	사용자 가상주소 http://localhost:8080/myweb/member/update
	// System.out.println("MemberController update() ");
	// member/updateForm.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update() {
		System.out.println("MemberController update() ");
		return "member/updateForm";
	}
	
	//	사용자 가상주소 http://localhost:8080/myweb/member/updatePro
	// System.out.println("MemberController updatePro() ");
	// 가상주소 로그인주소 이동 /member/main (주소줄에 주소가 바뀌면서 이동)
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro() {
		System.out.println("MemberController updatePro() ");
		return "redirect:/member/main";
	}
	
//	사용자 가상주소 http://localhost:8080/myweb/member/delete
	// System.out.println("MemberController delete() ");
	// member/deleteForm.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		System.out.println("MemberController delete() ");
		return "member/deleteForm";
	}
	
	//	사용자 가상주소 http://localhost:8080/myweb/member/deletePro
	// System.out.println("MemberController deletePro() ");
	// 가상주소 로그인주소 이동 /member/main (주소줄에 주소가 바뀌면서 이동)
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro() {
		System.out.println("MemberController deletePro() ");
		return "redirect:/member/main";
	}
	
//	사용자 가상주소 http://localhost:8080/myweb/member/list
	// System.out.println("MemberController list() ");
	// member/list.jsp 이동  주소줄이 안바뀌면서 이동
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {
		System.out.println("MemberController list() ");
		return "member/list";
	}
	
}//
