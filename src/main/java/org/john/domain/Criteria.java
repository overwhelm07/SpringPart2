package org.john.domain;

/**
 * Created by JeongHeon on 2016. 7. 7..
 *
 * 검색 기준 분류 기준  sql limit startpagenum, perpagenum을 보여주기 위해서
 */
public class Criteria {
    private int page;
    private int perPageNum;

    public Criteria(){
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page <= 0){
            this.page = 1;
            return;
        }
        this.page = page;
    }

    //for MyBatis SQL Mapper
    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        if(perPageNum<=0 || perPageNum > 100){
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    //for MyBatis SQL Mapper
    public int getPageStart(){
        //sql limit구문에서 시작 위치를 지정할 때 사용
        //즉, 시작 데이터 번호
        return (this.page - 1) * perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}
