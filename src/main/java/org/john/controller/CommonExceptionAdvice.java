package org.john.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * Created by JeongHeon on 2016. 7. 7..
 *
 * 예외 처리
 * @ControllerAdvice
 * 메소드에 @Exceptionhandler를 이용해서 적절한 Exception처리
 */

@ControllerAdvice
public class CommonExceptionAdvice {

    /*@ExceptionHandler(Exception.class)
    public String common(Exception e){
        System.out.println(e.toString());

        return "error_common";
    }*/
    //ModelAndView는 모델 데이터와 뷰의 처리를 동시에 할 수 있는 객체
    @ExceptionHandler(Exception.class)
    private ModelAndView errorModelAndView(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error_common");
        modelAndView.addObject("exception", ex);

        return modelAndView;
    }
}
