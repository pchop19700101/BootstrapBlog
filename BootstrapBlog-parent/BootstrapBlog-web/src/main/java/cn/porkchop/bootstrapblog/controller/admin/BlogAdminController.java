package cn.porkchop.bootstrapblog.controller.admin;

import cn.porkchop.bootstrapblog.pojo.Blog;
import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.TBlog;
import cn.porkchop.bootstrapblog.service.BlogService;
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
    private BlogService blogService;

    /**
     * 添加博客
     *
     * @date 2018/3/6 19:11
     * @author porkchop
     */
    @RequestMapping("/add")
    @ResponseBody
    public HashMap<String, String> add(TBlog tBlog) throws IOException, SolrServerException {
        blogService.add(tBlog);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }

    /**
     * 查询所有,可以指定标题的模糊查询
     *
     * @date 2018/3/6 19:14
     * @author porkchop
     */
    @RequestMapping("findForDatagrid")
    @ResponseBody
    public EasyUIDataGridResult<Blog> findForDatagrid(@RequestParam(defaultValue = "") String partTitle, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int rows) {
        return blogService.findForDatagrid(partTitle, page, rows);
    }

    /**
     * 删除博文
     *
     * @date 2018/3/6 22:03
     * @author porkchop
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> delete(String ids) throws IOException, SolrServerException {
        blogService.delete(ids.split(","));
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }

    /**
     * 更新博客
     *
     * @date 2018/3/7 20:22
     * @author porkchop
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> update(TBlog tBlog) throws IOException, SolrServerException {
        blogService.update(tBlog);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }

    /**
     * 后台根据id查询博客
     *
     * @date 2018/3/7 20:22
     * @author porkchop
     */
    @RequestMapping("findById")
    public String findById(Long id, Model model) {
        TBlog tBlog = blogService.findByIdAdmin(id);
        model.addAttribute("tBlog", tBlog);
        return "admin/modifyBlog";
    }

    /**
     * 删除所有索引,并重新导入
     *
     * @date 2018/3/10 12:24
     * @author porkchop
     */
    @RequestMapping("deleteAllAndReimportIndex")
    @ResponseBody
    public HashMap<String, String> deleteAllAndReimportIndex() throws IOException, SolrServerException {
        blogService.deleteAllAndReimportIndex();
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "success");
        return map;
    }
}
