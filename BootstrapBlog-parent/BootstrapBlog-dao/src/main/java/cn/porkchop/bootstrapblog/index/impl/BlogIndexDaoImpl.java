package cn.porkchop.bootstrapblog.index.impl;

import cn.porkchop.bootstrapblog.index.BlogIndexDao;
import cn.porkchop.bootstrapblog.pojo.PageBean;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import cn.porkchop.bootstrapblog.utils.HTMLUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BlogIndexDaoImpl implements BlogIndexDao {
    @Autowired
    private SolrServer solrServer;

    @Override
    public void insertOrUpdate(List<TBlog> list) throws IOException, SolrServerException {
        for (TBlog tBlog : list) {
            // 创建Document对象
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", tBlog.getId());
            doc.addField("blog_title", tBlog.getTitle());
            doc.addField("blog_summary", tBlog.getSummary());
            doc.addField("blog_releaseDate", new SimpleDateFormat("yyyy-MM-dd hh:mm").format(tBlog.getReleaseDate()));
            doc.addField("blog_content", HTMLUtils.stripHtml(tBlog.getContent()));
            doc.addField("blog_keyword", tBlog.getKeyword());
            // 将Document对象添加到索引库
            solrServer.add(doc);
        }
        solrServer.commit();
    }

    @Override
    public void deleteById(Long id) throws IOException, SolrServerException {
        solrServer.deleteByQuery("id:" + id);
        solrServer.commit();
    }

    @Override
    public void deleteAll() throws IOException, SolrServerException {
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

    @Override
    public PageBean<TBlog> search(SolrQuery query,int currentPage,int paginationCount) throws SolrServerException, ParseException {
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        long totalCount = solrDocumentList.getNumFound();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<TBlog> list = new ArrayList<>();
        for (SolrDocument doc : solrDocumentList) {
            //封装到对象中
            TBlog tBlog = new TBlog();
            tBlog.setId(Long.valueOf((String) doc.get("id")));
            tBlog.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse((String) doc.get("blog_releaseDate")));
            tBlog.setSummary((String) doc.get("blog_summary"));
            //把高亮信息封装到对象中
            Map<String, List<String>> docHighlight = highlighting.get(doc.get("id"));
            //封装title
            List<String> titleHighlightList = docHighlight.get("blog_title");
            if (titleHighlightList != null && !titleHighlightList.isEmpty()) {
                tBlog.setTitle(titleHighlightList.get(0));
            } else {
                tBlog.setTitle((String) doc.get("blog_title"));
            }
            //封装summary
            List<String> summaryHighlight = docHighlight.get("blog_summary");
            if (summaryHighlight != null && !summaryHighlight.isEmpty()) {
                tBlog.setSummary(summaryHighlight.get(0));
            } else {
                tBlog.setSummary((String) doc.get("blog_summary"));
            }
            list.add(tBlog);
        }
        return new PageBean<TBlog>(currentPage,query.getRows(),totalCount,list,paginationCount);
    }
}
