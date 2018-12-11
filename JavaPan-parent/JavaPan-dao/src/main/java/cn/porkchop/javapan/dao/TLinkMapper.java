package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.TLink;
import cn.porkchop.javapan.pojo.TLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLinkMapper {
    int countByExample(TLinkExample example);

    int deleteByExample(TLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLink record);

    int insertSelective(TLink record);

    List<TLink> selectByExample(TLinkExample example);

    TLink selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLink record, @Param("example") TLinkExample example);

    int updateByExample(@Param("record") TLink record, @Param("example") TLinkExample example);

    int updateByPrimaryKeySelective(TLink record);

    int updateByPrimaryKey(TLink record);
}