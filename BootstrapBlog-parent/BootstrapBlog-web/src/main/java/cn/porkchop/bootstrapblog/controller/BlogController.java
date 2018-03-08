package cn.porkchop.bootstrapblog.controller;

import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.TComment;
import cn.porkchop.bootstrapblog.service.BlogService;
import cn.porkchop.bootstrapblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

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
        if(blog==null){
            return "redirect:/index.html";
        }
        model.addAttribute("blog", blog);
        Blog nextBlog = blogService.findNext(blog);
        model.addAttribute("nextBlog", nextBlog);
        Blog preBlog = blogService.findPre(blog);
        model.addAttribute("preBlog", preBlog);
        //获取评论信息
        List<TComment> commentList = commentService.findByBlogId(blogId);
        model.addAttribute("commentList",commentList);
        return "foreground/blog";
    }
}
