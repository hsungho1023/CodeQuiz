package com.codequiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codequiz.service.QuizBoardService;
import com.codequiz.vo.QuizBoardVo;
import com.codequiz.vo.QuizCorrectCountVo;

@Controller
public class QuizBoardController {
	@Autowired
	private QuizBoardService service;
	
	/**
	 * 퀴즈 게시판 컨트롤러.
	 * @param category 퀴즈 게시판에서 사용할 카테고리 분류용 매개변수. 0: 모든 게시물, 1: 자바, 2: SQL, 3: HTML, 4: CSS.
	 * @param page 현재 페이지 번호
	 * @param session 세션
	 * @return 퀴즈 목록, 시작 페이지, 끝 페이지, 카테고리를 반환하여 QuizBoard 로 보냄.
	 */
	@RequestMapping("/quizBoard")
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
			mv.setViewName("QuizBoard/QuizBoard");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	
	/** 
	 * 퀴즈 게시판 게시물 보기 컨트롤러.
	 * @param quizNum 해당 퀴즈의 Primary key 번호값.
	 * @param session 세션
	 * @return 
	 */
	@RequestMapping("/quizDetail")
	public ModelAndView quizDetail(int quizNum, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		// 선택한 퀴즈 게시물의 정보를 가져오며, 조회수를 증가시킨 뒤 최신화함.
		QuizBoardVo vo = service.selectQuiz(quizNum);
		int viewCheck = 0;
		viewCheck = service.updateQuizView(quizNum);
		if (viewCheck != 0) {
			vo.setQuizView(service.selectQuizView(quizNum));
		} 
		ModelAndView mv = new ModelAndView();
		// 로그인 확인 후, 모든 정보를 출력하기 위해 vo에서 꺼내며 해당 퀴즈를 맞춘 이력이 있는지 확인한다. 
		// 로그인 실패시 로그인 페이지로 전달한다.
		if (memberId != null && memberId.length() > 0) {
			mv.addObject("history", service.selectQuizCorrectHistory(memberId, quizNum));
			mv.addObject("vo", vo);
			mv.setViewName("QuizBoard/QuizDetail");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	
	/**
	 * 퀴즈 게시판 글쓰기 양식 페이지 컨트롤러.
	 * @param memberId 현재 로그인한 작성자 아이디.
	 * @param session 세션
	 * @return 
	 */
	@RequestMapping("/quizWriteForm")
	public ModelAndView quizWriteForm(HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		ModelAndView mv = new ModelAndView();
		if (memberId != null && memberId.length() > 0) {
			mv.setViewName("QuizBoard/QuizWriteForm");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	
	/** 
	 * 퀴즈 게시판 글쓰기 컨트롤러.
	 * @param vo 사용자가 새 글을 쓰기위해 입력한 값들.
	 * @param session 세션
	 * @return 작성 후에 QuizBoard 로 이동.
	 */
	@RequestMapping(value = "/quizWrite", method = RequestMethod.POST)
	public String quizWrite(QuizBoardVo vo, @RequestParam("selectedQuizAnswer") String selectedQuizAnswer, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		int sqa = Integer.parseInt(selectedQuizAnswer);
		System.out.println(selectedQuizAnswer);
		// 로그인 확인 후, 성공 시에는 해당 게시물 DB에 입력, 실패 시 QuizBoard 로 이동.
		if (memberId != null && memberId.length() > 0) {
			// 카테고리 값에 맞게 치환
			switch(Integer.parseInt(vo.getQuizType())) {
				case 1 : 
					vo.setQuizType("JAVA");
					break;
				case 2 :
					vo.setQuizType("SQL");
					break;
				case 3 :
					vo.setQuizType("HTML");
					break;
				case 4 :
					vo.setQuizType("CSS");
					break;
			}
			vo.setQuizTrueAnswer(sqa);
			vo.setQuizWriter(memberId);
			service.insertQuiz(vo);
		} 
		return "redirect:/quizBoard?category=0&page=1";
	}
	
	/**
	 * 회원이 선택한 퀴즈의 정답 여부를 Javascript의 alert() 를 이용해 알려줍니다.
	 * 만약에 이미 맞춘 이력이 있을 경우, 정답으로는 뜨지만 DB에 중복으로는 입력되지 않습니다. 
	 * @param quizNum 퀴즈 고유 번호.
	 * @param selectedQuizAnswer 회원이 선택한 정답 번호.
	 * @param session 세션
	 * @return 성공 시에는 1, 실패 시에는 0을 반환함.
	 */
	@RequestMapping(value = "/quizResult", method = RequestMethod.POST)
	public ModelAndView quizResult(int quizNum, @RequestParam("selectedQuizAnswer") String selectedQuizAnswer, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		ModelAndView mv = new ModelAndView();
		int sqa = Integer.parseInt(selectedQuizAnswer);
		// 로그인 확인 후, 성공 시에는 해당 퀴즈 정답 DB에 입력, 실패 시 QuizBoard 로 이동.
		if (memberId != null && memberId.length() > 0) {
			QuizCorrectCountVo vo = new QuizCorrectCountVo(memberId, quizNum);
			mv.addObject("quizResult", service.insertQuizAnswer(vo, sqa));
			mv.addObject("quizNum", quizNum);
			mv.setViewName("QuizBoard/QuizResult");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	
	/**
	 * 퀴즈 게시판 수정 폼 컨트롤러.
	 * @param quizNum 퀴즈 고유 번호.
	 * @param session 세션.
	 * @return 성공 시 QuizUpdateForm 으로 이동
	 */
	@RequestMapping(value = "/quizUpdateForm", method=RequestMethod.POST)
	public ModelAndView quizUpdateForm(int quizNum, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		ModelAndView mv = new ModelAndView();
		// 퀴즈 번호를 바탕으로 퀴즈 정보를 가져옴.
		QuizBoardVo vo = service.selectQuiz(quizNum);
		// 로그인 확인 후, 성공 시에는 정보를 가지고 Form 으로 이동, 실패 시 QuizBoard 로 이동.
		if (memberId != null && memberId.length() > 0) {
			mv.addObject("vo", vo);
			mv.setViewName("QuizBoard/QuizUpdateForm");
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	

	/**
	 * 퀴즈 수정 컨트롤러.
	 * @param quizNum 퀴즈 고유 번호.
	 * @param session 세션.
	 * @return 퀴즈 수정 후 Javascript alert() 로 결과를 반환. 
	 */
	@RequestMapping(value = "/quizUpdate", method=RequestMethod.POST)
	public ModelAndView quizUpdate(int quizNum, QuizBoardVo vo, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		QuizBoardVo original = service.selectQuiz(quizNum);
		vo.setQuizTrueAnswer(original.getQuizTrueAnswer());
		vo.setQuizWriter(original.getQuizWriter());
		vo.setQuizWriteDate(original.getQuizWriteDate());
		ModelAndView mv = new ModelAndView();
		// 로그인 확인 후, 성공 시에는 해당 퀴즈 정답 DB에 입력, 실패 시 QuizBoard 로 이동.
		if (memberId != null && memberId.length() > 0) {
			// 글 작성자와 로그인 아이디가 일치하는 지 확인 후 Javascript alert() 으로 결과를 알린 뒤 QuizBoard 로 이동.
			if(memberId.equals(vo.getQuizWriter())) {
				// 카테고리 값에 맞게 치환
				switch(Integer.parseInt(vo.getQuizType())) {
				
					case 1 : 
						vo.setQuizType("JAVA");
						break;
					case 2 :
						vo.setQuizType("SQL");
						break;
					case 3 :
						vo.setQuizType("HTML");
						break;
					case 4 :
						vo.setQuizType("CSS");
						break;
				}
				mv.addObject("result", service.updateQuiz(vo));
				mv.addObject("quizNum", quizNum);
				mv.setViewName("QuizBoard/QuizUpdate");
			} else {
				mv.addObject("result", -1);
				mv.addObject("quizNum", quizNum);
				mv.setViewName("QuizBoard/QuizUpdate");
			}
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
	
	/**
	 * 퀴즈 삭제 컨트롤러.
	 * @param quizNum 퀴즈 고유 번호.
	 * @param session 세션.
	 * @return 퀴즈 수정 후 Javascript alert() 로 결과를 반환.
	 */
	@RequestMapping(value = "/quizDelete", method = RequestMethod.POST)
	public ModelAndView quizDelete(int quizNum, HttpSession session) {
		// 로그인 확인용.
		String memberId = (String)session.getAttribute("memberId");
		// 로그인한 유저의 맞춘 총 퀴즈 수.
		session.setAttribute("memberCorrect", service.selectMemberCorrectCount(memberId));
		session.getAttribute("memberCorrect");
		ModelAndView mv = new ModelAndView();
		// 퀴즈 번호를 바탕으로 퀴즈 작성자를 가져옴.
		String quizWriter = service.selectQuiz(quizNum).getQuizWriter();
		// 로그인 확인 후, 성공 시에는 해당 퀴즈 정답 DB에 입력, 실패 시 QuizBoard 로 이동.
		if (memberId != null && memberId.length() > 0) {
			// 글 작성자와 로그인 아이디가 일치하는 지 확인 후 Javascript alert() 으로 결과를 알린 뒤 QuizBoard 로 이동.
			// 일치할 경우 동작, 아닐 경우 -1 반환으로 실패.
			if(memberId.equals(quizWriter)) {
				// 퀴즈를 맞춘 기록과 해당 퀴즈를 삭제함.
				service.deleteQuizCorrectCount(quizNum);
				int result =  service.deleteQuiz(quizNum);
				mv.addObject("result", result);
				mv.setViewName("QuizBoard/QuizDelete");
			} else {
				mv.addObject("result", -1);
				mv.setViewName("QuizBoard/QuizDelete");
			}
		} else {
			mv.setViewName("redirect:loginForm");
		}
		return mv;
	}
}
