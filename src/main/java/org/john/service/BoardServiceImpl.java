package org.john.service;

import org.john.domain.BoardVO;
import org.john.domain.Criteria;
import org.john.domain.SearchCriteria;
import org.john.persistence.BoardDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 5..
 */
@Service
public class BoardServiceImpl implements BoardService{
    @Inject
    private BoardDAO dao;

    @Override
    public void regist(BoardVO board) throws Exception {
        dao.create(board);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        dao.updateViewCnt(bno);
        return dao.read(bno);
    }

    @Override
    public void modify(BoardVO board) throws Exception {
        dao.update(board);
    }

    @Override
    public void remove(Integer bno) throws Exception {
        dao.delete(bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return dao.listAll();
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return dao.listCriteria(cri);
    }

    @Override
    public int listCountCriteria(Criteria cri) throws Exception {
        return dao.countPaging(cri);
    }

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return dao.listSearchCount(cri);
    }
}
