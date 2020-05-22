package com.hong.service;

public class BoardPager {
	public static final int PAGE_SCALE = 10;
	public static final int BLOCK_SCALE = 10;
	private int curPage;
	private int prevPage;
	private int nextPage;
	private int totPage;
	private int totBlock;
	private int curBlock;
	private int prevBlock;
	private int nextBlock;
	private int pageBegin;
	private int pageEnd;
	private int blockBegin;
	private int blockEnd;
	
	public BoardPager(int count, int curPage) {
		curBlock = 1;
		this.curPage = curPage;
		setTotPage(count);
		setPageRange();
		setTotBlock();
		setBlockRange();
	}

	public void setBlockRange() {
		curBlock = (int)Math.ceil((curPage-1)/BLOCK_SCALE+1); // 현재 페이지가 몇번째 페이지 블록에 속하는지 계산 
		blockBegin = (curBlock-1)*BLOCK_SCALE+1; // 현재페이지 블록시작과 끝 번호 계산
		blockEnd = blockBegin+BLOCK_SCALE-1;
		if(blockEnd > totPage) {blockEnd = totPage;} // 마지막 블록이 범위초과하지 않도록
		prevPage = (curPage == 1)?1:(curBlock-1)*BLOCK_SCALE; // 이전 누르면 이동할 페이지 번호
		nextPage = curBlock>totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
		if(nextPage >= totPage) nextPage = totPage;  // 마지막 페이지가 범위를 초과하지 않도록 처리 
	}
	
	public void setPageRange() {
		pageBegin = (curPage-1)*PAGE_SCALE;
		pageEnd = 10;
	}

	// getter setter
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int PrevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int NextPage) {
		this.nextPage = nextPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int count) {
		totPage = (int) Math.ceil(count*1.0/PAGE_SCALE);
	}
	public int getTotBlock() {
		return totBlock;
	}
	public void setTotBlock() {
		totBlock = (int) Math.ceil(totPage/BLOCK_SCALE)+1;
	}
	public int getCurBlock() {
        return curBlock;
    }
    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }
    public int getPrevBlock() {
        return prevBlock;
    }
    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }
    public int getNextBlock() {
        return nextBlock;
    }
    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    public int getPageBegin() {
        return pageBegin;
    }
    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }
    public int getPageEnd() {
        return pageEnd;
    }
    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    public int getBlockBegin() {
        return blockBegin;
    }
    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }
    public int getBlockEnd() {
        return blockEnd;
    }
    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
}
