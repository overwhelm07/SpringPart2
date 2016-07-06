package org.john.controller;

import org.apache.ibatis.annotations.ResultType;
import org.john.domain.BoardVO;
import org.john.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by JeongHeon on 2016. 7. 6..
 */
@Controller
@RequestMapping("/board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService service;

    //GET은 입력 페이지와 조회 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET(BoardVO board, Model model) throws Exception{
        System.out.println("register get!!!");
        logger.info("register get!!!");
    }

    //POST는 외부에서 정보를 입력하는 경우에 (주소창에 보여지면 안되는 정보)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
        System.out.println("register post!!!");
        System.out.println(board.toString());
        logger.info("register post!!!");
        logger.info(board.toString());

        service.regist(board);

        rttr.addFlashAttribute("msg", "success");
        //model.addAttribute("result", "success");
        //return "/board/success";
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public void listAll(Model model) throws Exception{
        System.out.println("show all list");
        /*List<BoardVO> list = service.listAll();
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).toString());
        }*/
        model.addAttribute("list", service.listAll());
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno, Model model) throws Exception{
        System.out.println("Read!!! and bno is " + bno);
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
        service.remove(bno);
        rttr.addFlashAttribute("msg", "success");
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyGET(int bno, Model model) throws Exception{
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
        System.out.println("mod post called");

        service.modify(board);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/board/listAll";
    }
}
