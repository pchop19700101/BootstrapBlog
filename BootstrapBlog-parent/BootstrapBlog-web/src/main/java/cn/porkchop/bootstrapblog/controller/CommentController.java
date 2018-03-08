package cn.porkchop.bootstrapblog.controller;

import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.TComment;
import cn.porkchop.bootstrapblog.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 提交评论
     *
     * @date 2018/3/2 23:23
     * @author porkchop
     */
    @RequestMapping("/submitComment")
    @ResponseBody
    public Map<String, String> submitComment(String captcha, TComment tComment, HttpServletRequest request,Blog blog) {
        HashMap<String, String> map = new HashMap<>();
        if (StringUtils.isNotBlank(captcha)) {
            String captchaInSession = (String)request.getSession().getAttribute("captcha");
            if(captcha.equals(captchaInSession)){
                tComment.setUserIp(request.getRemoteAddr());
                commentService.add(tComment);
                map.put("message","评论成功!");
                return map;
            }
            map.put("message","验证码错误!");
            return map;
        }
        //验证码没填
        map.put("message","请输入验证码!");
        return map;
    }

}
