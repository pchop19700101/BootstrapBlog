package cn.porkchop.javapan.controller;

import cn.porkchop.javapan.pojo.Blog;
import cn.porkchop.javapan.pojo.TUser;
import cn.porkchop.javapan.service.BlogService;
import cn.porkchop.javapan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Value("${INDEX_BLOG_SIZE}")
    private int INDEX_BLOG_SIZE;
    @Value("${INDEX_PAGINATION_COUNT}")
    private int INDEX_PAGINATION_COUNT;

    /**
     * 显示博客列表,包含分页,类别,日期查询
     *
     * @date 2018/3/1 12:16
     * @author porkchop
     */
    @RequestMapping("/index")
    public String goIndex(Blog blog, @RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request, Model model) {
        PageInfo<Blog> pageInfo = blogService.findByCondition(pageNum, INDEX_BLOG_SIZE, INDEX_PAGINATION_COUNT, blog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("lastPageNum", pageNum);
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(blog.getReleaseMonth())) {
            builder.append("&releaseMonth=" + blog.getReleaseMonth());
        }
        if (blog.getTypeId() != null) {
            builder.append("&typeId=" + blog.getTypeId());
        }
        model.addAttribute("params", builder.toString());
        return "foreground/index";
    }

    /**
     * 跳转到关于我页面
     *
     * @date 2018/3/8 20:11
     * @author porkchop
     */
    @RequestMapping("/aboutme")
    public String aboutMe(Model model) {
        TUser tUser = userService.findById(1);
        model.addAttribute("user", tUser);
        return "foreground/aboutme";
    }

    /**
     * 跳转到登陆页面
     *
     * @date 2018/3/8 20:11
     * @author porkchop
     */
    @RequestMapping("/login")
    public String goLogin() {
        return "login";
    }

    /**
     * 后台系统跳转controller
     *
     * @date 2018/3/8 20:11
     * @author porkchop
     */
    @RequestMapping("/admin/page/{page}")
    public String goAdminPage(@PathVariable String page) {
        return "admin/" + page;
    }
}