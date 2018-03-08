package cn.porkchop.bootstrapblog.service;

import cn.porkchop.bootstrapblog.pojo.TAdvertisement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdvertisementService {
    /**
     * 查询所有
     *
     * @date 2018/2/28 21:38
     * @author porkchop
     */
    List<TAdvertisement> findAllByOrder();
}
