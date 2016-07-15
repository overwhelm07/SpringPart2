package org.john.persistence;

import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.apache.ibatis.session.SqlSession;
import org.john.domain.Criteria;
import org.john.domain.ReplyVO;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JeongHeon on 2016. 7. 10..
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO{
    @Inject
    private SqlSession session;

    private static String namespace = "org.john.mapper.ReplyMapper";

    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return session.selectList(namespace + ".list", bno);
    }

    @Override
    public void create(ReplyVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(Integer rno) throws Exception {
        session.update(namespace + ".delete", rno);
    }

    @Override
    public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("bno", bno);
        paramMap.put("cri", cri);

        return session.selectList(namespace + ".listPage", paramMap);
    }

    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(namespace + ".count", bno);
    }
}
