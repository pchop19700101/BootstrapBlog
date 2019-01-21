package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.Blog;

import java.util.List;

public interface BlogMapper {
    /**
     * 根据月份排序,查询出所有月份,包含每月的数量
     *
     * @author porkchop
     */
    List<Blog> findReleaseMonthWithCount();

    /**
     * 根据条件查询
     *
     * @author porkchop
     */
    List<Blog> findByCondition(Blog blog);

    /**
     * 根据博客id查询,包含博客类别名
     *
     * @author porkchop
     */
    Blog findWithBlogTypeNameById(Long blogId);

    /**
     * 查找下一篇
     *
     * @author porkchop
     */
    Blog findNext(Blog blog);

    /**
     * 查找上一篇
     *
     * @author porkchop
     */
    Blog findPre(Blog blog);

    /**
     * 查询所有,可以指定标题的模糊查询
     *
     * @author porkchop
     */
    List<Blog> findForDatagrid(String partTitle);


}