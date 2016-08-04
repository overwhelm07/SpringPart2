package org.john.persistence;

import org.john.domain.Criteria;
import org.john.domain.ReplyVO;

import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 10..
 */
public interface ReplyDAO {
    //댓글 리스트
    public List<ReplyVO> list(Integer bno) throws Exception;

    //댓글 등록
    public void create(ReplyVO vo) throws Exception;
    //수정
    public void update(ReplyVO vo) throws Exception;
    //삭제
    public void delete(Integer rno) throws Exception;

    public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

    public int count(Integer bno) throws Exception;



    public int getBno(Integer rno) throws Exception;
}
