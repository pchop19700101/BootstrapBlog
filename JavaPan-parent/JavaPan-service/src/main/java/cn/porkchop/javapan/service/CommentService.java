package cn.porkchop.javapan.service;

import cn.porkchop.javapan.pojo.Comment;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TComment;

import java.util.List;

public interface CommentService {
    /**
     * 根据博客id查找评论
     *
     * @date 2018/3/2 19:04
     * @author porkchop
     */
    List<TComment> findByResourceId(Long blogId);

    /**
     * 添加评论
     *
     * @date 2018/3/2 23:19
     * @author porkchop
     */
    void add(TComment tComment);

    /**
     * 显示所有的评论
     *
     * @date 2018/3/8 15:23
     * @author porkchop
     */
    EasyUIDataGridResult<Comment> findAllWithBlogNameAndBlogId(int page, int rows);

    /**
     * 批量删除
     * @date 2018/3/8 15:35
     * @author porkchop
     */
    void delete(String ids);
}
