package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.TLinkMapper;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TLink;
import cn.porkchop.javapan.pojo.TLinkExample;
import cn.porkchop.javapan.service.LinkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    /**
     * 查询全部
     */
    @Override
    public List<TLink> findAll() {
        return tLinkMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public EasyUIDataGridResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TLink> page = (Page<TLink>) tLinkMapper.selectByExample(null);
        return new EasyUIDataGridResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TLink tLink) {
        tLinkMapper.insert(tLink);
    }


    /**
     * 修改
     */
    @Override
    public void update(TLink tLink) {
        tLinkMapper.updateByPrimaryKey(tLink);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TLink findOne(Long id) {
        return tLinkMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tLinkMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public EasyUIDataGridResult findPage(TLink tLink, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TLinkExample example = new TLinkExample();
        TLinkExample.Criteria criteria = example.createCriteria();

        if (tLink != null) {
            if (tLink.getLinkName() != null && tLink.getLinkName().length() > 0) {
                criteria.andLinkNameLike("%" + tLink.getLinkName() + "%");
            }
            if (tLink.getLinkUrl() != null && tLink.getLinkUrl().length() > 0) {
                criteria.andLinkUrlLike("%" + tLink.getLinkUrl() + "%");
            }

        }

        Page<TLink> page = (Page<TLink>) tLinkMapper.selectByExample(example);
        return new EasyUIDataGridResult(page.getTotal(), page.getResult());
    }


}
