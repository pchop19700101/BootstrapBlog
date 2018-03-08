package cn.porkchop.bootstrapblog.dao;

import cn.porkchop.bootstrapblog.pojo.TBlog;
import cn.porkchop.bootstrapblog.pojo.TBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBlogMapper {
    int countByExample(TBlogExample example);

    int deleteByExample(TBlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TBlog record);

    int insertSelective(TBlog record);

    List<TBlog> selectByExampleWithBLOBs(TBlogExample example);

    List<TBlog> selectByExample(TBlogExample example);

    TBlog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TBlog record, @Param("example") TBlogExample example);

    int updateByExampleWithBLOBs(@Param("record") TBlog record, @Param("example") TBlogExample example);

    int updateByExample(@Param("record") TBlog record, @Param("example") TBlogExample example);

    int updateByPrimaryKeySelective(TBlog record);

    int updateByPrimaryKeyWithBLOBs(TBlog record);

    int updateByPrimaryKey(TBlog record);
}