package cn.porkchop.javapan.controller.admin;

import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.Result;
import cn.porkchop.javapan.pojo.TAdvertisement;
import cn.porkchop.javapan.service.AdvertisementService;
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
@RequestMapping("/admin/advertisement")
public class AdvertisementAdminController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TAdvertisement> findAll() {
        return advertisementService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public EasyUIDataGridResult findPage(int page, int rows) {
        return advertisementService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param advertisement
     * @return
     */
    @RequestMapping("/add")
    public Result add(TAdvertisement advertisement) {
        try {
            advertisementService.add(advertisement);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param advertisement
     * @return
     */
    @RequestMapping("/update")
    public Result update(TAdvertisement advertisement) {
        try {
            advertisementService.update(advertisement);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @return
     */
    @RequestMapping("/findOne")
    public TAdvertisement findOne(Long id) {
        return advertisementService.findOne(id);
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
            advertisementService.delete(ids);
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
    public EasyUIDataGridResult search(TAdvertisement advertisement, int page, int rows) {
        return advertisementService.findPage(advertisement, page, rows);
    }

}
