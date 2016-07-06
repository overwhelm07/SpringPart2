package org.john.persistence;

import org.john.domain.BoardVO;
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
}
