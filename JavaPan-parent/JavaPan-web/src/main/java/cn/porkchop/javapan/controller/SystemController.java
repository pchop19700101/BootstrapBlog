package cn.porkchop.javapan.controller;

import cn.porkchop.javapan.pojo.TUser;
import cn.porkchop.javapan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class SystemController {
    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private BackgroundImageService backgroundImageService;

    /**
     * 刷新缓存
     *
     * @date 2018/3/8 20:10
     * @author porkchop
     */
    @RequestMapping("/refresh")
    @ResponseBody
    public HashMap<String, String> refresh(TUser user, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        //博客类型
        servletContext.setAttribute("blogTypes", blogTypeService.findAllWithBlogCountByOrder());
        //友情链接
        servletContext.setAttribute("links", linkService.findAllByOrder());
        //月份
        servletContext.setAttribute("monthes", resourceService.findReleaseMonthWithCount());
        //广告
        servletContext.setAttribute("advertisements", advertisementService.findAllByOrder());
        //背景图片
        servletContext.setAttribute("backgroundImage", backgroundImageService.findById(1));
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }
}
