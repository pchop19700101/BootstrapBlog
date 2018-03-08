package cn.porkchop.bootstrapblog.service;

import cn.porkchop.bootstrapblog.pojo.BlogType;
import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.TBlogType;

import java.util.List;

public interface BlogTypeService {
    /**
     * 根据序号排序,查询所有,并且包含此类别的文章数量
     *
     * @date 2018/2/28 20:32
     * @author porkchop
     */
    List<BlogType> findAllWithBlogCountByOrder();

    /**
     * 查询所有
     *
     * @date 2018/3/8 10:57
     * @author porkchop
     * @param page
     * @param rows
     */
    EasyUIDataGridResult<TBlogType> findAll(int page, int rows);

    /**
     * 添加
     * @date 2018/3/8 13:17
     * @author porkchop
     */
    void add(TBlogType tBlogType);

    /**
     * 修改
     * @date 2018/3/8 13:19
     * @author porkchop
     */
    void update(TBlogType tBlogType);

    /**
     *删除
     * @date 2018/3/8 13:21
     * @author porkchop
     */
    void delete(String ids);
}
