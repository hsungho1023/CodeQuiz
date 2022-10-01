package com.codequiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codequiz.service.QnABoardService;
import com.codequiz.service.QuizBoardService;
import com.codequiz.vo.QnABoardVo;

@Controller
public class QnABoardController {
	@Autowired
	private QnABoardService qnaBoardService;
	@Autowired
	private QuizBoardService quizBoardService;
	
	@RequestMapping(value = "/qnaBoard", method = RequestMethod.GET)
	public ModelAndView qnaBoard(@RequestParam(defaultValue = "1") int page, HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		if(memberId != null && memberId.length() > 0) {
			// 로그인한 유저의 맞춘 총 퀴즈 수.
			session.setAttribute("memberCorrect", quizBoardService.selectMemberCorrectCount(memberId));
			session.getAttribute("memberCorrect");
			// 로그인한 유저의 맞춘 퀴즈 수 총 퍼센테이지.
			session.setAttribute("correctPercent", quizBoardService.selectQuizCorrectPercent(memberId));
			session.getAttribute("correctPercent");
		}
		// 퀴즈 게시판 카테고리 초기화.
		session.removeAttribute("category");
		ModelAndView mv = new ModelAndView();
		mv.addObject("qnaPage", qnaBoardService.makeQnAPage(page));
		mv.setViewName("QnABoard/QnABoard");
		return mv;
	}

	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm(HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		if (memberId != null && memberId.length() > 0) {
			return "QnABoard/QnAWriteForm";
		} else {
			return "redirect:loginForm";
		}
	}

	@RequestMapping("/qnaWrite")
	public String qnaWrite(QnABoardVo qna, HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		// 로그인 한 사람만 할 수 있게
		if (memberId != null && memberId.length() > 0) {
			qnaBoardService.insertQnA(qna, memberId);
			return "QnABoard/QnAWriteResult";
		} else {
			return "redirect:loginForm";
		}
	}

	@RequestMapping("/qnaDetail")
	public ModelAndView qnaDetail(int qnaNum, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String memberId = (String) session.getAttribute("memberId");
		QnABoardVo qna = qnaBoardService.selectQnA(qnaNum, memberId);
		mv.addObject("qna", qna);
		mv.setViewName("QnABoard/QnADetail");
		return mv;
	}

	@RequestMapping("/qnaUpdateForm")
	public ModelAndView qnaUpdateForm(int qnaNum) {
		QnABoardVo qna = qnaBoardService.readNoCount(qnaNum);
		ModelAndView mv = new ModelAndView();
		mv.addObject("real", qna);
		mv.setViewName("QnABoard/QnAUpdateForm");
		return mv;
	}

	@RequestMapping(value = "/qnaUpdate", method = RequestMethod.POST)
	public ModelAndView qnaUpdate(QnABoardVo qna, HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		boolean result = qnaBoardService.updateQnA(qna, memberId);
		ModelAndView mv = new ModelAndView();

		if (result) {
			mv.addObject("qnaNum", qna.getQnaNum());
			mv.setViewName("QnABoard/QnAUpdateSuccess");
		} else {
			mv.setViewName("QnABoard/QnAUpdateFailed");
		}

		return mv;
	}

	@RequestMapping("/qnaDelete")
	public ModelAndView qnaDelete(int qnaNum, HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		boolean result = qnaBoardService.deleteQnA(qnaNum, memberId);

		ModelAndView mv = new ModelAndView();

		mv.addObject("result", result);
		mv.setViewName("QnABoard/QnADelete");
		return mv;
	}
}
