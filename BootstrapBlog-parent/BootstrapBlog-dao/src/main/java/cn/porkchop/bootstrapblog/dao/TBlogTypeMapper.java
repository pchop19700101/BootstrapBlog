package cn.porkchop.bootstrapblog.dao;

import cn.porkchop.bootstrapblog.pojo.TBlogType;
import cn.porkchop.bootstrapblog.pojo.TBlogTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBlogTypeMapper {
    int countByExample(TBlogTypeExample example);

    int deleteByExample(TBlogTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBlogType record);

    int insertSelective(TBlogType record);

    List<TBlogType> selectByExample(TBlogTypeExample example);

    TBlogType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBlogType record, @Param("example") TBlogTypeExample example);

    int updateByExample(@Param("record") TBlogType record, @Param("example") TBlogTypeExample example);

    int updateByPrimaryKeySelective(TBlogType record);

    int updateByPrimaryKey(TBlogType record);
}