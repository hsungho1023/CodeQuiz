package com.codequiz.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
/*import org.mybatis.spring.SqlSessionTemplate;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codequiz.repository.mapper.RankBoardMapper;

import com.codequiz.vo.RankBoardVo;

@Component
public class RankBoardDao {
	
	@Autowired 
	private SqlSessionTemplate session;	

	public List<RankBoardVo> selectJavaRank(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectJavaRank();
	}
	
	public List<RankBoardVo> selectSqlRank(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectSqlRank();
	}
	
	public List<RankBoardVo> selectHtmlRank(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectHtmlRank();
	}
	
	public List<RankBoardVo> selectCssRank(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectCssRank();
	}
	
	public List<RankBoardVo> selectJavaRankTopThree(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectJavaRankTopThree();
	}
	
	public List<RankBoardVo> selectSqlRankTopThree(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectSqlRankTopThree();
	}
	
	public List<RankBoardVo> selectHtmlRankTopThree(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectHtmlRankTopThree();
	}
	
	public List<RankBoardVo> selectCssRankTopThree(){
		RankBoardMapper mapper = session.getMapper(RankBoardMapper.class);
		return mapper.selectCssRankTopThree();
	}
}
