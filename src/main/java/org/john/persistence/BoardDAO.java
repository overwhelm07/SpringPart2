package org.john.persistence;

import org.john.domain.BoardVO;
import org.john.domain.Criteria;
import org.john.domain.SearchCriteria;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 5..
 */
public interface BoardDAO {
    public void create(BoardVO vo)throws Exception;

    public BoardVO read(Integer bno)throws Exception;

    public void update(BoardVO vo)throws Exception;

    public void delete(Integer bno)throws Exception;

    public List<BoardVO> listAll()throws Exception;

    public List<BoardVO> listPage(int page)throws Exception;

    public List<BoardVO> listCriteria(Criteria cri)throws Exception;

    public int countPaging(Criteria cri) throws Exception;

    public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;

    public int listSearchCount(SearchCriteria cri)throws Exception;

    //댓글 카운트 숫자 변경
    public void updateReplyCnt(Integer bno, int amount) throws Exception;

    public void updateViewCnt(Integer bno) throws Exception;
}
