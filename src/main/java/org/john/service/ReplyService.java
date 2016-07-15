package org.john.service;

import org.john.domain.Criteria;
import org.john.domain.ReplyVO;

import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 10..
 */
public interface ReplyService {
    public void addReply(ReplyVO vo) throws Exception;
    public List<ReplyVO> listReply(Integer bno) throws Exception;
    public void modifyReply(ReplyVO vo) throws Exception;
    public void removeReply(Integer rno) throws Exception;
    public List<ReplyVO> listReplyPage(Integer bno, Criteria cri)throws Exception;
    public int count(Integer bno) throws Exception;

}
