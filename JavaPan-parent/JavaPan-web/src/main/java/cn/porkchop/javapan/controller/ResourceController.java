package cn.porkchop.javapan.controller;

import cn.porkchop.javapan.pojo.Blog;
import cn.porkchop.javapan.pojo.PageBean;
import cn.porkchop.javapan.pojo.TBlog;
import cn.porkchop.javapan.pojo.TComment;
import cn.porkchop.javapan.service.CommentService;
import cn.porkchop.javapan.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CommentService commentService;
    @Value("${SEARCH_PAGINATION_COUNT}")
    private int SEARCH_PAGINATION_COUNT;
    @Value("${SEARCH_BLOG_SIZE}")
    private int SEARCH_BLOG_SIZE;

    /**
     * 显示博客页面
     *
     * @author porkchop
     */
    @RequestMapping("/{id}")
    public String showBlog(@PathVariable Long id, Model model) {
        //获取博客信息
        Blog blog = resourceService.findById(id);
        if (blog == null) {
            return "redirect:/index.html";
        }
        model.addAttribute("blog", blog);
        Blog nextBlog = resourceService.findNext(blog);
        model.addAttribute("nextBlog", nextBlog);
        Blog preBlog = resourceService.findPre(blog);
        model.addAttribute("preBlog", preBlog);
        //获取评论信息
        List<TComment> commentList = commentService.findByResourceId(id);
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
        queryString= new String(queryString.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        PageBean<TBlog> pageBean = resourceService.search(SEARCH_BLOG_SIZE, pageNum, queryString, SEARCH_PAGINATION_COUNT);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryString", queryString);
        return "foreground/search";
    }
}
