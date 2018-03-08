package cn.porkchop.bootstrapblog.service;

import cn.porkchop.bootstrapblog.pojo.TBackgroundImage;

public interface BackgroundImageService {
    /**
     * 根据id查找
     *
     * @date 2018/3/8 20:13
     * @author porkchop
     */
    TBackgroundImage findById(int id);
}
