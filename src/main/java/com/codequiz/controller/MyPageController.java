package com.codequiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codequiz.service.QuizBoardService;

@Controller
public class MyPageController {
	@Autowired
	private QuizBoardService service;
	
	/**
	 * 마이 페이지 게시판 컨트롤러.
	 * @param category 마이 페이지 게시판에서 사용할 카테고리 분류용 매개변수. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @param page 현재 페이지 번호
	 * @param session 세션
	 * @return 퀴즈 목록, 시작 페이지, 끝 페이지, 카테고리를 반환하여 MyPage 로 보냄.
	 */
	@RequestMapping("/myPage")
	public ModelAndView quizBoard(@RequestParam(defaultValue = "0") int category, @RequestParam(defaultValue = "1") int page, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		ModelAndView mv = new ModelAndView();
		// 카테고리 기본값을 모두 표시로 설정
		String stringCategory = "ALL";
		
		// 카테고리 값에 맞게 치환
		switch(category) {
			case 1 : 
				stringCategory = "JAVA";
				break;
			case 2 :
				stringCategory = "SQL";
				break;
			case 3 :
				stringCategory = "HTML";
				break;
			case 4 :
				stringCategory = "CSS";
				break;
			default :
				stringCategory = "ALL";
		}
		
		// 페이징에서 현재 페이지를 0 이하나 음수값, 또는 끝 페이지를 넘어가지 못하게 함. 
		if(page <= 0) {
			page = 1;
		} else if (service.boardPaging(page, stringCategory).getEndPage() < page) {
			page = service.boardPaging(page, stringCategory).getEndPage();
		}
		
		// 로그인 확인, 실패시 로그인 페이지로 전달.
		if (memberId != null && memberId.length() > 0) {
			// 로그인한 유저의 맞춘 총 퀴즈 수.
			session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
			session.getAttribute("memberCorrect");
			// 카테고리 변경 후에도 해당 선택을 고정하기 위해 세션에 전달.
			session.setAttribute("category", category);
			// 목록으로 돌아갈 때 사용할 현재 페이지의 수.
			session.setAttribute("currentPage", page);
			// 로그인한 유저의 맞춘 퀴즈 수 총 퍼센테이지.
			session.getAttribute("memberCorrect");
			mv.addObject("quizList", service.boardPaging(page, stringCategory));
			mv.addObject("startPage", service.boardPaging(page, stringCategory).getStartPage());
			mv.addObject("endPage", service.boardPaging(page, stringCategory).getEndPage());
			mv.addObject("category", category);
			mv.setViewName("MyPage/MyPage");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
}
