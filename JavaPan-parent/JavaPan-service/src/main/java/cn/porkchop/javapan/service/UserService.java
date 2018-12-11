package cn.porkchop.javapan.service;

import cn.porkchop.javapan.pojo.TUser;

public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @date 2018/2/28 17:30
     * @author porkchop
     */
    TUser findByUsername(String userName);

    /**
     * 根据id查找
     *
     * @date 2018/3/1 21:30
     * @author porkchop
     */
    TUser findById(long id);

    /**
     * 更新
     *
     * @date 2018/3/8 16:38
     * @author porkchop
     */
    void update(TUser user);

    /**
     * 修改密码
     *
     * @date 2018/3/8 19:23
     * @author porkchop
     */
    void changePassword(TUser user);
}
