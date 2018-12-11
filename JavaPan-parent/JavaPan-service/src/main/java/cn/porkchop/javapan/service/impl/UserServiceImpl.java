package cn.porkchop.javapan.service.impl;

import cn.porkchop.javapan.dao.TUserMapper;
import cn.porkchop.javapan.pojo.TUser;
import cn.porkchop.javapan.pojo.TUserExample;
import cn.porkchop.javapan.service.UserService;
import cn.porkchop.javapan.utils.CryptographyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser findByUsername(String userName) {
        TUserExample userExample = new TUserExample();
        TUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<TUser> list = tUserMapper.selectByExample(userExample);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public TUser findById(long id) {
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andIdEqualTo(id);
        List<TUser> tUsers = tUserMapper.selectByExampleWithBLOBs(tUserExample);
        return tUsers.get(0);
    }

    @Override
    public void update(TUser user) {
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andIdEqualTo(user.getId());
        tUserMapper.updateByExampleSelective(user, tUserExample);
    }

    @Override
    public void changePassword(TUser user) {
        user.setPassword(CryptographyUtil.md5(user.getPassword(), "cn.porkchop.javapan"));
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andIdEqualTo(user.getId());
        tUserMapper.updateByExampleSelective(user, tUserExample);
    }
}
