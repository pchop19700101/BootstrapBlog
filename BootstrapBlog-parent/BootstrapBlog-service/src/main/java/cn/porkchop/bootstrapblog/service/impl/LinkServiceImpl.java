package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.TLinkMapper;
import cn.porkchop.bootstrapblog.pojo.TLink;
import cn.porkchop.bootstrapblog.pojo.TLinkExample;
import cn.porkchop.bootstrapblog.service.LinkService;
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
