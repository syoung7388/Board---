package com.lcomputerstudy.testmvc.vo;






public class Pagination {

    int count;
	int page;// 현재 페이지 번호
	int pageNum; // userCount/page= 화면에 나타 낼 user index 번호
	int startPage; //시작
	int endPage; // 끝
	int lastPage;/// (userCount/화면에 표시할 갯수)-> page 마지막 번호
	int prevPage;///  이전 페이지
	int nextPage;/// 다음 페이지
	
	
	public static final int pageUnit=5; // 한번에 불러 올 pagination 수
	public static final int perPage=3; //  한번에 불러올 사용자 수


	
    public Pagination() {
	
     }


    public Pagination(int page, int count) {
    	this.page= page;
    	this.count= count;
    	
	    startPage = ((page-1)/pageUnit)*pageUnit+1;// ex) ((7-1)/5)*5 +1 =6	   
	    lastPage = (int)Math.ceil(count/(float)perPage);
	    endPage= startPage+pageUnit-1;
	    endPage = endPage< lastPage ? endPage:lastPage;
	    prevPage=(startPage-1);
	    nextPage=(startPage+pageUnit);
	    }
   
    
    
    
    public int getCount() {
    	return count;
    }
    public void setUserCount(int count) {
    	this.count= count;
    }
    
    public int getPage() {
    	return page;
    }
    public void setPage(int page) {
    	this.page = page;
    	
    }
    public int getPageNum() {
    	return pageNum;
    }
    public void setpageNum(int pageNum) {
    	this.pageNum= pageNum;
    }
    
    public int getStartPage() {
    	return startPage;
    }
    public void setStartPage(int startPage) {
    	this.startPage= startPage;
    }
    public int getEndPage() {
    	return endPage;
    }
    public void setEndPage(int endPage) {
    	this.endPage= endPage;
    }
    public int getLastPage() {
    	return lastPage;
    }
    public void setLastPage(int lastPage){
    	this.lastPage= lastPage;
    }
    
    
    public int getPageUnit() {
    	return pageUnit;
    }
    public int getPerPage() {
    	return perPage;
    }
    public int getPrevPage() {
    	return prevPage;
    }
    public void setPrevPage(int prevPage) {
    	this.prevPage= prevPage;
    }
    public int getNextPage() {
    	return nextPage;
    }
    public void setNextPage(int nextPage) {
    	this.nextPage= nextPage;
    }
    

    
       
}

