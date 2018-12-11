package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.TBackgroundImageMapper;
import cn.porkchop.javapan.pojo.TBackgroundImage;
import cn.porkchop.javapan.service.BackgroundImageService;
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
