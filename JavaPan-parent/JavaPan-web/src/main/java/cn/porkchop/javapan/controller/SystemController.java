package cn.porkchop.javapan.controller;

import cn.porkchop.javapan.pojo.TBlog;
import cn.porkchop.javapan.pojo.TUser;
import cn.porkchop.javapan.service.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class SystemController {
    @Autowired
    private ResourceTypeService resourceTypeService;

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
     */
    @RequestMapping("/refresh")
    @ResponseBody
    public HashMap<String, String> refresh(TUser user, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        //博客类型
        servletContext.setAttribute("blogTypes", resourceTypeService.findAllWithBlogCountByOrder());
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

    /**
     * 添加博客
     */
    @RequestMapping("/add")
    @ResponseBody
    public HashMap<String, String> add(TBlog tBlog) throws IOException, SolrServerException {
        //html和xml代码转义
        tBlog.setSummary(StringEscapeUtils.escapeHtml4(tBlog.getSummary()));
        resourceService.add(tBlog);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }
}
