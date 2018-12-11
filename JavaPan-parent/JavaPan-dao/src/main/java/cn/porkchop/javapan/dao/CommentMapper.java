package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    /**
     * 根据博客id查询评论数量
     *
     * @date 2018/3/3 10:53
     * @author porkchop
     */
    Long findCountByBlogId(Long blogId);

    /**
     * 显示所有的评论
     *
     * @date 2018/3/8 15:23
     * @author porkchop
     */
    List<Comment> findAllWithBlogNameAndBlogId();
}
