package org.john.service;

import org.john.domain.BoardVO;
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
}
