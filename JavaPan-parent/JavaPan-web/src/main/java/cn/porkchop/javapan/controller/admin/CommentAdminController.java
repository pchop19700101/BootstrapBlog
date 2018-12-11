package cn.porkchop.javapan.controller.admin;

import cn.porkchop.javapan.pojo.Comment;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
    @Autowired
    private CommentService commentService;

    /**
     * 显示所有的评论
     *
     * @date 2018/3/8 15:23
     * @author porkchop
     */
    @RequestMapping("/findAllWithBlogNameAndBlogId")
    @ResponseBody
    public EasyUIDataGridResult<Comment> findAllWithBlogNameAndBlogId(int page, int rows) {
        return commentService.findAllWithBlogNameAndBlogId(page, rows);
    }

    /**
     * 批量删除
     *
     * @date 2018/3/8 15:35
     * @author porkchop
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, String> delete(String ids) {
        commentService.delete(ids);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }
}
