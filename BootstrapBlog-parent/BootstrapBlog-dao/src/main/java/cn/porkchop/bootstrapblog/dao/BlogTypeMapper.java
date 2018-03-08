package cn.porkchop.bootstrapblog.dao;

import cn.porkchop.bootstrapblog.pojo.BlogType;
import cn.porkchop.bootstrapblog.pojo.TBlogType;
import cn.porkchop.bootstrapblog.pojo.TBlogTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTypeMapper {
    /**
     * 根据序号排序,查询所有,并且包含此类别的文章数量
     *
     * @date 2018/2/28 20:32
     * @author porkchop
     */
    List<BlogType> findAllWithBlogCountByOrder();
}