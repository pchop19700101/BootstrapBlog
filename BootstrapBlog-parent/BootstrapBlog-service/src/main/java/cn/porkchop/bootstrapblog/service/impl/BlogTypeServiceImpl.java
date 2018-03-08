package cn.porkchop.bootstrapblog.service.impl;

import cn.porkchop.bootstrapblog.dao.BlogTypeMapper;
import cn.porkchop.bootstrapblog.dao.TBlogTypeMapper;
import cn.porkchop.bootstrapblog.pojo.*;
import cn.porkchop.bootstrapblog.service.BlogTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
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
