package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.BlogType;
import cn.porkchop.javapan.pojo.TBlogType;
import cn.porkchop.javapan.pojo.TBlogTypeExample;

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