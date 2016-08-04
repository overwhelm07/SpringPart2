package org.john.service;

import org.john.domain.BoardVO;
import org.john.domain.Criteria;
import org.john.domain.SearchCriteria;

import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 5..
 * 비즈니스 계층 구현(컨트롤러와 DAO사이의 접착제 역할)
 * 특징
 * 비즈니스 계층은 고객마다 다른 부분을 처리할 수 있는 완충장치 역할을 한다
 * 각 회사마다 다른 로직이나 규칙을 디비에 무관하게 처리할 수 있는 완충 영역으로 존재할 필요가 있다
 * 컨트롤러와 같은 외부 호출이 영속 계층에 종속적인 상황을 막아준다
 * 만약 컨트롤러가 직접 영속 계층의 디비를 이용하게 되면 트랜잭션의 처리나 예외의 처리 등 모든 로직이 컨트롤러로 집중된다
 * 비즈니스계층은 컨트롤러로 하여금 처리해야 하는 일을 분업하게 만들어 준다
 */
public interface BoardService {
    public void regist(BoardVO board) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public void modify(BoardVO board) throws Exception;

    public void remove(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listCriteria(Criteria cri) throws Exception;

    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

    public int listSearchCount(SearchCriteria cri)throws Exception;

    public int listCountCriteria(Criteria cri) throws Exception;

    public List<String> getAttach(Integer bno) throws Exception;
}
