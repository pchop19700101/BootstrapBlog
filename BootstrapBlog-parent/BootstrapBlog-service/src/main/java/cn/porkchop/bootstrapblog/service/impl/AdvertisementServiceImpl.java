package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.TAdvertisementMapper;
import cn.porkchop.bootstrapblog.pojo.TAdvertisement;
import cn.porkchop.bootstrapblog.pojo.TAdvertisementExample;
import cn.porkchop.bootstrapblog.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private TAdvertisementMapper tAdvertisementMapper;

    @Override
    public List<TAdvertisement> findAllByOrder() {
        TAdvertisementExample tAdvertisementExample = new TAdvertisementExample();
        tAdvertisementExample.setOrderByClause("order_num asc");
        return tAdvertisementMapper.selectByExample(tAdvertisementExample);
    }
}
