package cn.porkchop.bootstrapblog.service;

import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.PageBean;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BlogService {
    /**
     * 根据月份排序,查询出所有月份,包含每月的博客数量
     *
     * @date 2018/2/28 21:18
     * @author porkchop
     */
    List<Blog> findReleaseMonthWithCount();

    /**
     * 分页查询所有博客,根据数据发布日期排序
     *
     * @date 2018/3/1 12:33
     * @author porkchop
     */
    PageInfo<Blog> findByCondition(int pageNum, int size, int paginationSize, Blog blog);

    /**
     * 根据id查询,同时增加一次点击数
     *
     * @date 2018/3/2 11:18
     * @author porkchop
     */
    Blog findById(Long blogId);

    /**
     * 查找下一篇博客
     *
     * @date 2018/3/2 17:35
     * @author porkchop
     */
    Blog findNext(Blog blog);

    /**
     * 查找上一篇博客
     *
     * @date 2018/3/2 17:35
     * @author porkchop
     */
    Blog findPre(Blog blog);

    /**
     * 添加博客
     *
     * @date 2018/3/5 20:13
     * @author porkchop
     */
    void add(TBlog tBlog) throws IOException, SolrServerException;

    /**
     * 查询所有,可以指定标题的模糊查询
     *
     * @date 2018/3/6 19:24
     * @author porkchop
     */
    EasyUIDataGridResult<Blog> findForDatagrid(String partTitle, int page, int rows);


    /**
     * 删除博文
     *
     * @date 2018/3/6 21:08
     * @author porkchop
     */
    void delete(String[] split) throws IOException, SolrServerException;

    /**
     * 更新博文
     *
     * @param blog
     * @date 2018/3/7 19:38
     * @author porkchop
     */
    void update(TBlog blog) throws IOException, SolrServerException;

    /**
     * 后台根据id查询
     *
     * @date 2018/3/7 20:25
     * @author porkchop
     */
    TBlog findByIdAdmin(Long id);


    /**
     * 删除所有索引,并重新导入
     *
     * @date 2018/3/10 12:24
     * @author porkchop
     */
    void deleteAllAndReimportIndex() throws IOException, SolrServerException;

    /**
     * 博客全文检索
     *
     * @date 2018/3/10 15:22
     * @author porkchop
     */
    PageBean<TBlog> search(int pageSize, int currentPage,String queryString,int paginationCoun) throws ParseException, SolrServerException;
}
