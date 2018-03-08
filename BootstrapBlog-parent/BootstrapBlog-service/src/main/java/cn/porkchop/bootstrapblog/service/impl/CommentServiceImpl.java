package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.CommentMapper;
import cn.porkchop.bootstrapblog.dao.TCommentMapper;
import cn.porkchop.bootstrapblog.pojo.Comment;
import cn.porkchop.bootstrapblog.pojo.EasyUIDataGridResult;
import cn.porkchop.bootstrapblog.pojo.TComment;
import cn.porkchop.bootstrapblog.pojo.TCommentExample;
import cn.porkchop.bootstrapblog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private TCommentMapper tCommentMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<TComment> findByBlogId(Long blogId) {
        TCommentExample tCommentExample = new TCommentExample();
        tCommentExample.createCriteria().andBlogIdEqualTo(blogId);
        List<TComment> tComments = tCommentMapper.selectByExample(tCommentExample);
        return tComments;
    }

    @Override
    public void add(TComment tComment) {
        tComment.setCommentDate(new Date());
        tComment.setState(0);
        tCommentMapper.insert(tComment);
    }

    @Override
    public EasyUIDataGridResult<Comment> findAllWithBlogNameAndBlogId(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Comment> list =commentMapper.findAllWithBlogNameAndBlogId();
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return new EasyUIDataGridResult<Comment>(pageInfo.getTotal(), list);
    }

    @Override
    public void delete(String ids) {
        for(String id:ids.split(",")){
            tCommentMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }
}
