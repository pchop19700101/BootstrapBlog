package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.BlogMapper;
import cn.porkchop.bootstrapblog.dao.TBlogMapper;
import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import cn.porkchop.bootstrapblog.pojo.TBlogExample;
import cn.porkchop.bootstrapblog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TBlogMapper tBlogMapper;

    @Override
    public List<Blog> findReleaseMonthWithCount() {
        return blogMapper.findReleaseMonthWithCount();
    }

    @Override
    public PageInfo<Blog> findByCondition(int pageNum, int size, int paginationSize, Blog blog) {
        PageHelper.startPage(pageNum, size);
        List<Blog> blogList = blogMapper.findByCondition(blog);


        for (Blog b : blogList) {
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
        if(blog==null){
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
    public void add(TBlog tBlog) {
        tBlog.setClickCount(0L);
        tBlog.setReleaseDate(new Date());
        tBlogMapper.insertSelective(tBlog);
    }

    @Override
    public EasyUIDataGridResult<Blog> findForDatagrid(String partTitle, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Blog> list = blogMapper.findForDatagrid(partTitle);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return new EasyUIDataGridResult<>(pageInfo.getTotal(), list);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            tBlogMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public void update(TBlog tBlog) {
        tBlogMapper.updateByPrimaryKeySelective(tBlog);
    }

    @Override
    public TBlog findByIdAdmin(Long id) {
        return tBlogMapper.selectByPrimaryKey(id);
    }
}
