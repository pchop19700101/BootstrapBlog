package cn.porkchop.javapan.service;

import cn.porkchop.javapan.pojo.TLink;

import java.util.List;

public interface LinkService {
    /**
     * 查询所有
     * @date 2018/2/28 19:36
     * @author porkchop
     */
    List<TLink> findAllByOrder();
}
