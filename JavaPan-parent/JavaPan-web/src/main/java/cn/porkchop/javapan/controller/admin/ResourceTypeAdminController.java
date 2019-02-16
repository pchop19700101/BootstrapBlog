package cn.porkchop.javapan.controller.admin;

import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TBlogType;
import cn.porkchop.javapan.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/blogType")
public class ResourceTypeAdminController {
    @Autowired
    private ResourceTypeService resourceTypeService;

    /**
     * 查询所有
     *
     * @date 2018/3/8 10:57
     * @author porkchop
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public EasyUIDataGridResult<TBlogType> findAll(int page , int rows) {
        return resourceTypeService.findAll(page,rows);
    }

    /**
     * 添加
     * @date 2018/3/8 13:16
     * @author porkchop
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(TBlogType tBlogType){
        resourceTypeService.add(tBlogType);

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
        resourceTypeService.update(tBlogType);

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
        resourceTypeService.delete(ids);
        HashMap<String, String> map = new HashMap<>();
        map.put("message","ok");
        return map;
    }
}
