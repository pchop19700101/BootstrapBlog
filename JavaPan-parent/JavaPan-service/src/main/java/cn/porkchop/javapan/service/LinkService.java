package cn.porkchop.javapan.service;

import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TLink;

import java.util.List;

public interface LinkService {
    /**
     * 查询所有
     */
    List<TLink> findAllByOrder();

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TLink> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    EasyUIDataGridResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TLink link);


    /**
     * 修改
     */
    void update(TLink link);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TLink findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 分页
     *
     * @param pageNum
     *         当前页 码
     * @param pageSize
     *         每页记录数
     * @return
     */
    EasyUIDataGridResult findPage(TLink link, int pageNum, int pageSize);
}
