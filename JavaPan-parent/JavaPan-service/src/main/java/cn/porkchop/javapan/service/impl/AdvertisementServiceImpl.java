package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.TAdvertisementMapper;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TAdvertisement;
import cn.porkchop.javapan.pojo.TAdvertisementExample;
import cn.porkchop.javapan.service.AdvertisementService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    /**
     * 查询全部
     */
    @Override
    public List<TAdvertisement> findAll() {
        return tAdvertisementMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public EasyUIDataGridResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TAdvertisement> page = (Page<TAdvertisement>) tAdvertisementMapper.selectByExample(null);
        return new EasyUIDataGridResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TAdvertisement tAdvertisement) {
        tAdvertisementMapper.insert(tAdvertisement);
    }


    /**
     * 修改
     */
    @Override
    public void update(TAdvertisement tAdvertisement) {
        tAdvertisementMapper.updateByPrimaryKey(tAdvertisement);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TAdvertisement findOne(Long id) {
        return tAdvertisementMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tAdvertisementMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public EasyUIDataGridResult findPage(TAdvertisement tAdvertisement, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TAdvertisementExample example = new TAdvertisementExample();
        TAdvertisementExample.Criteria criteria = example.createCriteria();

        if (tAdvertisement != null) {
            if (tAdvertisement.getImageUrl() != null && tAdvertisement.getImageUrl().length() > 0) {
                criteria.andImageUrlLike("%" + tAdvertisement.getImageUrl() + "%");
            }
            if (tAdvertisement.getComment() != null && tAdvertisement.getComment().length() > 0) {
                criteria.andCommentLike("%" + tAdvertisement.getComment() + "%");
            }
            if (tAdvertisement.getLinkUrl() != null && tAdvertisement.getLinkUrl().length() > 0) {
                criteria.andLinkUrlLike("%" + tAdvertisement.getLinkUrl() + "%");
            }

        }

        Page<TAdvertisement> page = (Page<TAdvertisement>) tAdvertisementMapper.selectByExample(example);
        return new EasyUIDataGridResult(page.getTotal(), page.getResult());
    }
}
