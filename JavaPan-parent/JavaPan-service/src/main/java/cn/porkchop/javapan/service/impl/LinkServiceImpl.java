package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.TLinkMapper;
import cn.porkchop.javapan.pojo.TLink;
import cn.porkchop.javapan.pojo.TLinkExample;
import cn.porkchop.javapan.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private TLinkMapper tLinkMapper;

    @Override
    public List<TLink> findAllByOrder() {
        TLinkExample tLinkExample = new TLinkExample();
        tLinkExample.setOrderByClause("order_num asc");
        return tLinkMapper.selectByExample(tLinkExample);
    }
}
