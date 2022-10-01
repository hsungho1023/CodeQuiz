package com.codequiz.repository.mapper;

import java.util.List;

import com.codequiz.vo.RankBoardVo;

public interface RankBoardMapper {

	public List<RankBoardVo> selectJavaRank();

	public List<RankBoardVo> selectSqlRank();

	public List<RankBoardVo> selectHtmlRank();

	public List<RankBoardVo> selectCssRank();

	public List<RankBoardVo> selectJavaRankTopThree();

	public List<RankBoardVo> selectSqlRankTopThree();

	public List<RankBoardVo> selectHtmlRankTopThree();

	public List<RankBoardVo> selectCssRankTopThree();

}
