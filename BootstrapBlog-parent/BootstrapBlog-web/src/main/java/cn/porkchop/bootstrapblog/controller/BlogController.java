package cn.porkchop.bootstrapblog.controller;

import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.PageBean;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import cn.porkchop.bootstrapblog.pojo.TComment;
import cn.porkchop.bootstrapblog.service.BlogService;
import cn.porkchop.bootstrapblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Value("${SEARCH_PAGINATION_COUNT}")
    private int SEARCH_PAGINATION_COUNT;
    @Value("${SEARCH_BLOG_SIZE}")
    private int SEARCH_BLOG_SIZE;

    /**
     * 显示博客页面
     *
     * @date 2018/3/2 18:59
     * @author porkchop
     */
    @RequestMapping("/{blogId}")
    public String showBlog(@PathVariable Long blogId, Model model) {
        //获取博客信息
        Blog blog = blogService.findById(blogId);
        if (blog == null) {
            return "redirect:/index.html";
        }
        model.addAttribute("blog", blog);
        Blog nextBlog = blogService.findNext(blog);
        model.addAttribute("nextBlog", nextBlog);
        Blog preBlog = blogService.findPre(blog);
        model.addAttribute("preBlog", preBlog);
        //获取评论信息
        List<TComment> commentList = commentService.findByBlogId(blogId);
        model.addAttribute("commentList", commentList);
        return "foreground/blog";
    }

    /**
     * 分页全文检索
     *
     * @date 2018/3/10 16:41
     * @author porkchop
     */
    @RequestMapping("/search")
    public String search(String queryString, @RequestParam(defaultValue = "1") int pageNum, Model model) throws Exception {
        queryString= new String(queryString.getBytes("iso8859-1"),"utf-8");
        PageBean<TBlog> pageBean = blogService.search(SEARCH_BLOG_SIZE, pageNum, queryString, SEARCH_PAGINATION_COUNT);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryString", queryString);
        return "foreground/search";
    }
}
