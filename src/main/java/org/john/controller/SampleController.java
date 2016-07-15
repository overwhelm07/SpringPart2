package org.john.controller;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.john.domain.SampleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JeongHeon on 2016. 7. 9..
 *
 * RestController용도
 * JSP와 같은 뷰를 만들어 내지 않는 대신에 데이터(문자열, JSON, XML 등 선택) 전달
 */
//특이한 점은 해당 컨트롤러의 모든 뷰 처리가 JSP가 아니다!
    //그리고 text/html타입의 데이터가 전송됨
@RestController
@RequestMapping("/sample")
public class SampleController {


    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    //이 SampleVO 객체를 전달하기 위해서는 JSON 타입으로 변환해서 전송해야 함 pom.xml에 (jackson-databind)라이브러리 추가
    @RequestMapping("/sendVO")
    public SampleVO sendVO(){
        SampleVO vo = new SampleVO();
        vo.setFirstName("길동");
        vo.setLastName("홍");
        vo.setMno(123);

        return vo;
    }

    @RequestMapping("/sendList")
    public List<SampleVO> sendList(){
        List<SampleVO> list = new ArrayList<SampleVO>();
        for(int i=0; i<10; i++){
            SampleVO vo = new SampleVO();
            vo.setFirstName("길동");
            vo.setLastName("홍");
            vo.setMno(i);
            list.add(vo);
        }
        return list;
    }

    @RequestMapping("/sendMap")
    public Map<Integer, SampleVO> sendMap(){
        Map<Integer, SampleVO> map = new HashMap<Integer, SampleVO>();
        for(int i=0; i<10; i++){
            SampleVO vo = new SampleVO();
            vo.setFirstName("길동");
            vo.setLastName("홍");
            vo.setMno(i);
            map.put(i, vo);
        }
        return map;
    }

    /*
    ResponseEntity타입은 개발자가 직접 결과 데이터 + HTTP의 상태 코드를 직접 제어할 수 있는 클래스이다
    이를 이용하면 좀 더 세밀한 제어가 필요한 경우에 사용해 볼 수 있다
     */
    @RequestMapping("/sendErrorAuth")
    public ResponseEntity<Void> sendListAuth(){
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping("/sendErrorNot")
    public ResponseEntity<List<SampleVO>> sendListNot(){
        List<SampleVO> list = new ArrayList<SampleVO>();
        for(int i=0; i<10; i++){
            SampleVO vo = new SampleVO();
            vo.setFirstName("길동");
            vo.setLastName("홍");
            vo.setMno(i);
            list.add(vo);
        }
        return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
    }

}
