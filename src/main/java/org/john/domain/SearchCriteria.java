package org.john.domain;

/**
 * Created by JeongHeon on 2016. 7. 7..
 */
public class SearchCriteria extends Criteria {
    private String searchType;
    private String keyword;

    public String getSearchType(){
        return searchType;
    }
    public void setSearchType(String searchType){
        this.searchType = searchType;
    }
    public String getKeyword(){
        return keyword;
    }
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "searchType='" + searchType + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
