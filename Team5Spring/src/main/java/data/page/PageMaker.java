package data.page;

import data.page.PageDto;

public class PageMaker {
	private int perPage;
	private int perBlock;

	public PageMaker(int perPage,int perBlock) {
		super();
		// TODO Auto-generated constructor stub
		this.perPage=perPage;
		this.perBlock=perBlock;
	}

	public PageDto page(int totalCount,int currentPage) {
		PageDto dto=new PageDto();
		
		int totalPage = totalCount/perPage+(totalCount%perPage>0?1:0);		
		int startPage = (currentPage-1)/perBlock*perBlock+1;
		int endPage = startPage+perBlock-1;
		if(endPage>totalPage)
			endPage=totalPage;
		int start = (currentPage-1)*perPage+1;
		int	end = start+perPage-1;		
		if(end> totalCount)
			end = totalCount;
		
		int no=totalCount-(currentPage-1)*perPage;

		dto.setTotalCount(totalCount);
		dto.setCurrentPage(currentPage);
		dto.setTotalPage(totalPage);
		dto.setStartPage(startPage);
		dto.setEndPage(endPage);
		dto.setStart(start);
		dto.setEnd(end);
		dto.setNo(no);
	
		return dto;
	}
}
