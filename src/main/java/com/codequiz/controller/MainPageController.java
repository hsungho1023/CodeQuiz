package com.codequiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codequiz.service.QuizBoardService;
import com.codequiz.service.RankBoardService;
import com.codequiz.service.QnABoardService;

@Controller
public class MainPageController {
	@Autowired
	private QuizBoardService quizBoardService;
	@Autowired
	private QnABoardService qnaBoardService; 
	@Autowired
	private RankBoardService rankBoardService;
	
	/**
	 * 메인 페이지 컨트롤러.
	 * @param session 세션
	 * @return 퀴즈 리스트, 해당 사용자가 맞춘 퀴즈의 퍼센테이지를 반환하여 MainPage 로 보냄.
	 */
	@RequestMapping("/mainPage")
	public ModelAndView mainPage(HttpSession session) {
		// 로그인 확인용.
		String memberId = (String) session.getAttribute("memberId");
		ModelAndView mv = new ModelAndView();
		
		// 로그인 확인 후 로그인 안됐으면 로그인 화면으로 보냄.
		if (memberId != null && memberId.length() > 0) {
			// 로그인한 유저의 맞춘 총 퀴즈 수.
			session.setAttribute("memberCorrect", quizBoardService.selectMemberCorrectCount(memberId));
			session.getAttribute("memberCorrect");
			// 로그인한 유저의 맞춘 퀴즈 수 총 퍼센테이지.
			session.setAttribute("correctPercent", quizBoardService.selectQuizCorrectPercent(memberId));
			session.getAttribute("correctPercent");
			// 퀴즈 게시판 카테고리 초기화.
			session.removeAttribute("category");
			// 랭크 이미지용 String 출력.
			session.setAttribute("rank", rankBoardService.rankIcon(memberId));
			// 메인페이지에 보여줄 최근 게시글 3개와 각 분류별 랭킹 상위 3명
			mv.addObject("quizList", quizBoardService.selectQuizLastThree());
			mv.addObject("qnaList", qnaBoardService.selectQnALastThree());
			mv.addObject("javaListThree", rankBoardService.selectJavaRankTopThree());
			mv.addObject("sqlListThree", rankBoardService.selectSqlRankTopThree());
			mv.addObject("htmlListThree", rankBoardService.selectHtmlRankTopThree());
			mv.addObject("cssListThree", rankBoardService.selectCssRankTopThree());
			mv.setViewName("MainPage");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
}
