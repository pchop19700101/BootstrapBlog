package cn.porkchop.bootstrapblog.controller.admin;

import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.TBlogType;
import cn.porkchop.bootstrapblog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 查询所有
     *
     * @date 2018/3/8 10:57
     * @author porkchop
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public EasyUIDataGridResult<TBlogType> findAll(int page , int rows) {
        return blogTypeService.findAll(page,rows);
    }

    /**
     * 添加
     * @date 2018/3/8 13:16
     * @author porkchop
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(TBlogType tBlogType){
        blogTypeService.add(tBlogType);

        HashMap<String, String> map = new HashMap<>();
        map.put("message","ok");
        return map;
    }

    /**
     * 修改
     * @date 2018/3/8 13:16
     * @author porkchop
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,String> update(TBlogType tBlogType){
        blogTypeService.update(tBlogType);

        HashMap<String, String> map = new HashMap<>();
        map.put("message","ok");
        return map;
    }
    /**
     * 删除
     * @date 2018/3/8 13:16
     * @author porkchop
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,String> delete(String ids){
        blogTypeService.delete(ids);
        HashMap<String, String> map = new HashMap<>();
        map.put("message","ok");
        return map;
    }
}
