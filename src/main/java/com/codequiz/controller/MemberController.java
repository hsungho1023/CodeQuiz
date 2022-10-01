package com.codequiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codequiz.service.MemberService;

import com.codequiz.vo.MemberVo;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	// 회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "Signup/JoinForm";

	}

	// 회원가입 로그인 중복여부
	@RequestMapping(value = "/joinForm/isMemberId", method = RequestMethod.POST)
	@ResponseBody
	public String isMemberId(String memberId) throws Exception {
		int result = service.isMemberId(memberId);
		System.out.println(result);
		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}

	// 회원가입 이용 약관
	@RequestMapping("/joinTerms")
	public String joinTerms() {
		return "Signup/JoinTerms";
	}

	// 회원가입 성공 , 실패
	@RequestMapping("/join")
	public ModelAndView join(MemberVo vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (service.join(vo)) {
			session.setAttribute("memberId", vo.getMemberId());
			mv.setViewName("Signup/JoinSuccess");
		} else {
			mv.setViewName("Signup/JoinFail");
		}
		return mv;
	}
	
	/* 로그인 폼 */
	@RequestMapping("/loginForm")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Signin/LoginForm");
		return mv;
	}
	
	/* 기본 값 로그인 폼 */
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Signin/LoginForm");
		return mv;
	}

	/* 로그인 성공 or 실패여부 */
	// HttpSession : 사용자 정보를 저장해서 관리하는 작업을 함(=로그인 세션처리).
	@RequestMapping(value = "/login", method = RequestMethod.POST) 
	public String login(String memberId, String memberPassword, HttpSession session, RedirectAttributes ra) {
		if (service.login(memberId, memberPassword)) {
			// 로그인 성공시 세션에 기록해두는 작업
			// setAttribut("key", value); 형태
			session.setAttribute("memberId", memberId);
			return "redirect:mainPage";
		} else {
			ra.addFlashAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			return "redirect:loginForm";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
