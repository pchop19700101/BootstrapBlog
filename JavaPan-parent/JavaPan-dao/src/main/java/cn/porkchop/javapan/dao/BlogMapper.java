package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.Blog;
import cn.porkchop.javapan.pojo.TBlog;
import cn.porkchop.javapan.pojo.TBlogExample;

import java.util.List;

public interface BlogMapper {
    /**
     * 根据月份排序,查询出所有月份,包含每月的博客数量
     *
     * @date 2018/2/28 21:18
     * @author porkchop
     */
    List<Blog> findReleaseMonthWithCount();

    /**
     * 根据条件查询
     *
     * @date 2018/3/1 19:22
     * @author porkchop
     */
    List<Blog> findByCondition(Blog blog);

    /**
     * 根据博客id查询,包含博客类别名
     *
     * @date 2018/3/2 17:37
     * @author porkchop
     */
    Blog findWithBlogTypeNameById(Long blogId);

    /**
     * 查找下一篇博客
     *
     * @date 2018/3/2 17:35
     * @author porkchop
     */
    Blog findNext(Blog blog);

    /**
     * 查找上一篇博客
     *
     * @date 2018/3/2 17:35
     * @author porkchop
     */
    Blog findPre(Blog blog);

    /**
     * 查询所有,可以指定标题的模糊查询
     *
     * @date 2018/3/6 19:14
     * @author porkchop
     */
    List<Blog> findForDatagrid(String partTitle);


}