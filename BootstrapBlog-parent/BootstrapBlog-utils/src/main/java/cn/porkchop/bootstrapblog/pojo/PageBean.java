package cn.porkchop.bootstrapblog.pojo;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
    /**
     * 当前第几页,传过来
     */
    private int currentPage;
    /**
     * 一页显示的数据条数,传过来
     */
    private int pageSize;
    /**
     * 总记录数,传过来
     */
    private long totalCount;
    /**
     * 数据列表,传过来
     */
    private List<T> list;
    /**
     * 分页条数据,生成
     */
    private List<Long> paginationList;
    /**
     * 分页条上显示的页数,传过来
     */
    private int paginationCount = 5;
    /**
     * 最后一页,计算
     */
    private long lastPage;

    /**
     * 上一页,计算
     */
    private long prePage;
    /**
     * 下一页,计算
     */
    private long nextPage;

    public PageBean(int currentPage, int pageSize, long totalCount, List<T> list, int paginationCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;
        this.paginationCount = paginationCount;
        //计算最后一页的页数
        lastPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        //计算分页条开始页数,结束页数
        long startPage = currentPage - (paginationCount / 2);
        startPage = startPage < 1 ? 1 : startPage;
        long endPage = startPage + (paginationCount - 1);
        endPage = endPage > lastPage ? lastPage : endPage;
        startPage = endPage - (paginationCount - 1);
        startPage = startPage < 1 ? 1 : startPage;
        //生成分页条
        paginationList = new ArrayList<>();
        for (long i = startPage; i <= endPage; i++) {
            paginationList.add(i);
        }
        //计算上一页和下一页
        nextPage = currentPage == lastPage ? 0 : currentPage + 1;
        prePage = currentPage == 1 ? 0 : currentPage - 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public List<Long> getPaginationList() {
        return paginationList;
    }

    public int getPaginationCount() {
        return paginationCount;
    }

    public long getLastPage() {
        return lastPage;
    }

    public long getPrePage() {
        return prePage;
    }

    public long getNextPage() {
        return nextPage;
    }
}
