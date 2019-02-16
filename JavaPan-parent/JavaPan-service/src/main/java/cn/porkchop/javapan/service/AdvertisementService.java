package cn.porkchop.javapan.service;

import cn.porkchop.javapan.pojo.EasyUIDataGridResult;
import cn.porkchop.javapan.pojo.TAdvertisement;

import java.util.List;

public interface AdvertisementService {
    /**
     * 查询所有
     *
     */
    List<TAdvertisement> findAllByOrder();

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TAdvertisement> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    EasyUIDataGridResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(TAdvertisement advertisement);


    /**
     * 修改
     */
    void update(TAdvertisement advertisement);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TAdvertisement findOne(Long id);


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
    EasyUIDataGridResult findPage(TAdvertisement advertisement, int pageNum, int pageSize);
}
