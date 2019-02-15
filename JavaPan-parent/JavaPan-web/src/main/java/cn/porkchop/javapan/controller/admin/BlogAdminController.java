package cn.porkchop.javapan.controller.admin;

import cn.porkchop.javapan.pojo.Blog;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TBlog;
import cn.porkchop.javapan.service.ResourceService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Autowired
    private ResourceService resourceService;

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

    /**
     * 查询所有,可以指定标题的模糊查询
     */
    @RequestMapping("findForDatagrid")
    @ResponseBody
    public EasyUIDataGridResult<Blog> findForDatagrid(@RequestParam(defaultValue = "") String partTitle, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int rows) {
        return resourceService.findForDatagrid(partTitle, page, rows);
    }

    /**
     * 删除博文
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> delete(String ids) throws IOException, SolrServerException {
        resourceService.delete(ids.split(","));
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }

    /**
     * 更新博客
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> update(TBlog tBlog) throws IOException, SolrServerException {
        //html和xml代码转义
        tBlog.setSummary(StringEscapeUtils.escapeHtml4(tBlog.getSummary()));
        resourceService.update(tBlog);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }

    /**
     * 后台根据id查询博客
     */
    @RequestMapping("findById")
    public String findById(Long id, Model model) {
        TBlog tBlog = resourceService.findByIdAdmin(id);
        model.addAttribute("tBlog", tBlog);
        return "admin/modifyBlog";
    }

    /**
     * 删除所有索引,并重新导入
     */
    @RequestMapping("deleteAllAndReimportIndex")
    @ResponseBody
    public HashMap<String, String> deleteAllAndReimportIndex() throws IOException, SolrServerException {
        resourceService.deleteAllAndReimportIndex();
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }
}
