package org.john.service;

import org.john.domain.BoardVO;
import org.john.domain.Criteria;
import org.john.domain.SearchCriteria;
import org.john.persistence.BoardDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 5..
 */
@Service
public class BoardServiceImpl implements BoardService {
    @Inject
    private BoardDAO dao;

    @Transactional
    @Override
    public void regist(BoardVO board) throws Exception {
        dao.create(board);

        String[] files = board.getFiles();

        if (files == null) {
            return;
        }
        for(String fileName : files){
            dao.addAttach(fileName);
        }
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        dao.updateViewCnt(bno);
        return dao.read(bno);
    }

    //원래의 게시물 수정 + 기존 첨부파일 목록 삭제 + 새로운 첨부파일 정보 입력
    @Transactional
    @Override
    public void modify(BoardVO board) throws Exception {
        dao.update(board);

        Integer bno = board.getBno();
        dao.deleteAttach(bno);
        String[] files = board.getFiles();

        if(files == null) { return; }

        for(String fileName : files){
            dao.replaceAttach(fileName, bno);
        }
    }

    @Transactional
    @Override
    public void remove(Integer bno) throws Exception {
        //삭제할 때 tbl_attach가 tbl_board를 참조하기 때문에 반드시 첨부파일 먼저 삭제 후 게시판 정보 삭제
        dao.deleteAttach(bno);
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

    @Override
    public List<String> getAttach(Integer bno) throws Exception {
        return dao.getAttach(bno);
    }
}
