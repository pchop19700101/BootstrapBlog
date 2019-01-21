package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.BlogMapper;
import cn.porkchop.javapan.dao.TBlogMapper;
import cn.porkchop.javapan.index.BlogIndexDao;
import cn.porkchop.javapan.pojo.*;
import cn.porkchop.javapan.service.ResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TBlogMapper tBlogMapper;
    @Autowired
    private BlogIndexDao blogIndexDao;

    @Override
    public List<Blog> findReleaseMonthWithCount() {
        return blogMapper.findReleaseMonthWithCount();
    }

    @Override
    public PageInfo<Blog> findByCondition(int pageNum, int size, int paginationSize, Blog blog) {
        PageHelper.startPage(pageNum, size);
        List<Blog> blogList = blogMapper.findByCondition(blog);
        for (Blog b : blogList) {
            //解析出博客缩略图
            ArrayList<String> imageList = new ArrayList<>();
            //获取前三张图片作为博客缩略图
            String blogInfo = b.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]");
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imageList.add(jpg.toString());
                if (i == 2) {
                    break;
                }
            }
            b.setImageList(imageList);
        }
        //获取分页信息
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList, paginationSize);
        return pageInfo;
    }

    @Override
    public Blog findById(Long blogId) {
        Blog blog = blogMapper.findWithBlogTypeNameById(blogId);
        if (blog == null) {
            return null;
        }
        //把字符串按空格切割
        List<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(blog.getKeyword().split(" ")));
        blog.setKeywordList(keywordList);
        //阅读量+1
        blog.setClickCount(blog.getClickCount() + 1);
        TBlogExample tBlogExample = new TBlogExample();
        tBlogExample.createCriteria().andIdEqualTo(blogId);
        tBlogMapper.updateByExampleSelective(blog, tBlogExample);
        return blog;
    }

    @Override
    public Blog findNext(Blog blog) {
        return blogMapper.findNext(blog);
    }

    @Override
    public Blog findPre(Blog blog) {
        return blogMapper.findPre(blog);
    }

    @Override
    public void add(TBlog tBlog) throws IOException, SolrServerException {
        tBlog.setClickCount(0L);
        tBlog.setReleaseDate(new Date());
        tBlogMapper.insertSelective(tBlog);
        List<TBlog> list = new ArrayList<>();
        list.add(tBlog);
        blogIndexDao.insertOrUpdate(list);
    }

    @Override
    public EasyUIDataGridResult<Blog> findForDatagrid(String partTitle, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Blog> list = blogMapper.findForDatagrid(partTitle);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return new EasyUIDataGridResult<>(pageInfo.getTotal(), list);
    }

    @Override
    public void delete(String[] ids) throws IOException, SolrServerException {
        for (String id : ids) {
            tBlogMapper.deleteByPrimaryKey(Long.valueOf(id));
            blogIndexDao.deleteById(Long.valueOf(id));
        }
    }

    @Override
    public void update(TBlog tBlog) throws IOException, SolrServerException {
        tBlogMapper.updateByPrimaryKeySelective(tBlog);
        ArrayList<TBlog> list = new ArrayList<>();
        list.add(tBlog);
        blogIndexDao.insertOrUpdate(list);
    }

    @Override
    public TBlog findByIdAdmin(Long id) {
        return tBlogMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteAllAndReimportIndex() throws IOException, SolrServerException {
        blogIndexDao.deleteAll();
        List<TBlog> list = tBlogMapper.selectByExampleWithBLOBs(new TBlogExample());
        blogIndexDao.insertOrUpdate(list);
    }

    @Override
    public PageBean<TBlog> search(int pageSize, int currentPage, String queryString, int paginationCount) throws ParseException, SolrServerException {
        //输入查询条件
        SolrQuery query = new SolrQuery();
        query.setQuery("blog_content:" + queryString +
                " or blog_title:" + queryString +
                " or blog_keyword:" + queryString);
        //设置按时间排序,新的放在前面
        query.setSort("blog_releaseDate", SolrQuery.ORDER.desc);
        //设置分页
        query.setStart(pageSize * (currentPage - 1));
        query.setRows(pageSize);
        //设置高亮
        query.setHighlight(true);
        //设置高亮显示的最大长度
        query.setHighlightFragsize(1000);
        query.addHighlightField("blog_title,blog_summary");
        query.setHighlightSimplePre("<span style='color:red; font-weight:bold!important'>");
        query.setHighlightSimplePost("</span>");
        return blogIndexDao.search(query, currentPage, paginationCount);
    }
}
