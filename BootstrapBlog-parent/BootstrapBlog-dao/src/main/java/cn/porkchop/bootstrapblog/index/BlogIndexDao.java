package cn.porkchop.bootstrapblog.index;

import cn.porkchop.bootstrapblog.pojo.PageBean;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BlogIndexDao {
    /**
     * 添加或修改索引
     *
     * @date 2018/3/9 21:38
     * @author porkchop
     */
    void insertOrUpdate(List<TBlog> list) throws IOException, SolrServerException;

    /**
     * 删除
     *
     * @date 2018/3/9 21:44
     * @author porkchop
     */
    void deleteById(Long id) throws IOException, SolrServerException;

    /**
     * 删除所有
     *
     * @date 2018/3/9 23:31
     * @author porkchop
     */
    void deleteAll() throws IOException, SolrServerException;

    /**
     * 查询
     *
     * @date 2018/3/10 13:39
     * @author porkchop
     */
    PageBean<TBlog> search(SolrQuery query,int currentPage,int paginationCoun) throws SolrServerException, ParseException;
}
