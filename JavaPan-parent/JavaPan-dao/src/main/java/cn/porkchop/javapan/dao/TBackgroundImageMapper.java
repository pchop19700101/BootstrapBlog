package cn.porkchop.javapan.dao;

import cn.porkchop.javapan.pojo.TBackgroundImage;
import cn.porkchop.javapan.pojo.TBackgroundImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBackgroundImageMapper {
    int countByExample(TBackgroundImageExample example);

    int deleteByExample(TBackgroundImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBackgroundImage record);

    int insertSelective(TBackgroundImage record);

    List<TBackgroundImage> selectByExampleWithBLOBs(TBackgroundImageExample example);

    List<TBackgroundImage> selectByExample(TBackgroundImageExample example);

    TBackgroundImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBackgroundImage record, @Param("example") TBackgroundImageExample example);

    int updateByExampleWithBLOBs(@Param("record") TBackgroundImage record, @Param("example") TBackgroundImageExample example);

    int updateByExample(@Param("record") TBackgroundImage record, @Param("example") TBackgroundImageExample example);

    int updateByPrimaryKeySelective(TBackgroundImage record);

    int updateByPrimaryKeyWithBLOBs(TBackgroundImage record);
}