package org.john.persistence;

import org.apache.ibatis.session.SqlSession;
import org.john.domain.BoardVO;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 5..
 */
@Repository
public class BoardDAOImpl implements BoardDAO{
    @Inject
    private SqlSession session;

    private static String namespace = "org.john.mapper.BoardMapper";

    @Override
    public void create(BoardVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return session.selectOne(namespace + ".read", bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(Integer bno) throws Exception {
        session.delete(namespace + ".delete", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace + ".listAll");
    }
}
