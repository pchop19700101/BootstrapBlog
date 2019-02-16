package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.BlogTypeMapper;
import cn.porkchop.javapan.dao.TBlogTypeMapper;
import cn.porkchop.javapan.pojo.BlogType;
import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TBlogType;
import cn.porkchop.javapan.pojo.TBlogTypeExample;
import cn.porkchop.javapan.service.ResourceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {
    @Autowired
    private BlogTypeMapper blogTypeMapper;
    @Autowired
    private TBlogTypeMapper tBlogTypeMapper;

    @Override
    public List<BlogType> findAllWithBlogCountByOrder() {

        List<BlogType> list = blogTypeMapper.findAllWithBlogCountByOrder();
        return list;
    }

    @Override
    public EasyUIDataGridResult<TBlogType> findAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TBlogType> list = tBlogTypeMapper.selectByExample(new TBlogTypeExample());
        PageInfo<TBlogType> pageInfo = new PageInfo<>(list);
        return new EasyUIDataGridResult<TBlogType>(pageInfo.getTotal(), list);
    }

    @Override
    public void add(TBlogType tBlogType) {
        tBlogTypeMapper.insertSelective(tBlogType);
    }

    @Override
    public void update(TBlogType tBlogType) {
        tBlogTypeMapper.updateByPrimaryKey(tBlogType);
    }

    @Override
    public void delete(String ids) {
        for(String id:ids.split(",")){
            tBlogTypeMapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
    }
}
