package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.TBackgroundImageMapper;
import cn.porkchop.bootstrapblog.pojo.TBackgroundImage;
import cn.porkchop.bootstrapblog.service.BackgroundImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackgroundImageServiceImpl implements BackgroundImageService {
    @Autowired
    private TBackgroundImageMapper tBackgroundImageMapper;

    @Override
    public TBackgroundImage findById(int id) {
        return tBackgroundImageMapper.selectByPrimaryKey(id);
    }
}
