package com.codequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codequiz.service.RankBoardService;

import com.codequiz.vo.RankBoardVo;

@Controller
public class RankBoardController {
	
	// vo > xml > Mapper(interface) > Dao > Service > Controller > html 

//	랭크페이지에 있는 selectAll 메소드를 호출하면
//	모든 멤버변수들이 담긴 RankVO친구 여러개를 데이터가 끝날때까지 불러와줌 ?
//  내가 이해한게 맞나 싶긴 한데 일단 적는거임 !
	
//	랭크페이지 (현 랭크보드) 로 가는 경로인 /rankBoard 를 입력 받으면 이 컨트롤러에서 모델앤뷰를 만들고 서비스의 메소드들 호출함.
//  흐름은 사용자 주소 입력 (여기선 /rankBoard) -> 맵핑된 컨트롤러 (지금 여기) -> 서비스에서 호출된 메소드 
//	-> Dao 에서 호출된 메소드 -> MyBatis 로 연동 (SqlSessionTemplate 를 통해) -> MapperInterface -> .xml 쿼리문 실행 후 값 출력
//	값은 역순으로 오게 되서 HTML 에 뿌려짐.
//	Vo는 데이터를 규격에 맞게 담는 상자 같은 역할임.
	
	@Autowired
	private RankBoardService service;
	
	@RequestMapping("/rankBoard")
	public ModelAndView ranking(RankBoardVo vo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("javaRankList", service.selectJavaRank());
		mv.addObject("sqlRankList", service.selectSqlRank());
		mv.addObject("htmlRankList", service.selectHtmlRank());
		mv.addObject("cssRankList", service.selectCssRank());
		mv.setViewName("RankBoard/RankBoard");
		return mv;
	}

}
