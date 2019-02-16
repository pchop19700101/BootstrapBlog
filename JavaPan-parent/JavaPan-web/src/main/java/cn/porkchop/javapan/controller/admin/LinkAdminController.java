package cn.porkchop.javapan.controller.admin;

import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.Result;
import cn.porkchop.javapan.pojo.TLink;
import cn.porkchop.javapan.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Autowired
    private LinkService linkService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TLink> findAll() {
        return linkService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public EasyUIDataGridResult findPage(int page, int rows) {
        return linkService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param link
     * @return
     */
    @RequestMapping("/add")
    public Result add(TLink link) {
        try {
            linkService.add(link);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param link
     * @return
     */
    @RequestMapping("/update")
    public Result update(TLink link) {
        try {
            linkService.update(link);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TLink findOne(Long id) {
        return linkService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            linkService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public EasyUIDataGridResult search(TLink link, int page, int rows) {
        return linkService.findPage(link, page, rows);
    }

}
