package org.john.web;

import org.john.domain.BoardVO;
import org.john.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by JeongHeon on 2016. 7. 5..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
    @Inject
    private BoardDAO dao;


    @Test
    public void testRead() throws Exception{
        logger.info(dao.read(2).toString());
    }
    @Test
    public void testUpdate() throws Exception{
        BoardVO board = new BoardVO();
        board.setBno(2);
        board.setTitle("수정된 글입니다.");
        board.setContent("수정 테스트");
        dao.update(board);
    }
    @Test
    public void testDelete() throws Exception{
        dao.delete(1);
    }
}
