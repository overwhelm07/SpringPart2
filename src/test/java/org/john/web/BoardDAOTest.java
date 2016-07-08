package org.john.web;

import org.john.domain.BoardVO;
import org.john.domain.Criteria;
import org.john.domain.SearchCriteria;
import org.john.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

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
    @Test
    public void testListPage() throws Exception{
        int page = 3;
        List<BoardVO> list = dao.listPage(page);
        for(BoardVO boardVO : list){
            System.out.println(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }
    @Test
    public void testListCriteria() throws Exception{
        Criteria cri = new Criteria();
        cri.setPage(2);
        cri.setPerPageNum(20);

        List<BoardVO> list = dao.listCriteria(cri);

        for(BoardVO boardVO : list){
            System.out.println(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }
    @Test
    public void testURI()throws Exception{
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance().path("/board/read").queryParam("bno", 12).queryParam("perPageNum", 20).build();

        System.out.println("\n/board/read?bno=12&perPageNum=20");
        System.out.println(uriComponents.toString());
    }

    @Test
    public void testURI2()throws Exception{
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance().path("/{module}/{page}").queryParam("bno", 12).queryParam("perPageNum", 20).build()
                .expand("board", "read").encode();

        System.out.println("\n/board/read?bno=12&perPageNum=20");
        System.out.println(uriComponents.toString());
    }

    @Test
    public void testDynamic1() throws Exception{
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("modify");
        cri.setSearchType("t");

        System.out.println("-------------------------");
        List<BoardVO> list = dao.listSearch(cri);

        for(BoardVO boardVO : list){
            System.out.println(boardVO.getBno() + ": " + boardVO.getTitle());
        }
        System.out.println("-------------------------");
        System.out.println("COUNT: " + dao.listSearchCount(cri));
    }
}
