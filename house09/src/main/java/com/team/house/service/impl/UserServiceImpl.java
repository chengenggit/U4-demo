package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.PageUtil;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUserByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //查询所有用户
        UsersExample usersExample = new UsersExample();
        //封装条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //动态sql:一个属性一个判断
        if(condition.getName()!=null)
            criteria.andNameLike("%"+condition.getName()+"%");
        if(condition.getTel()!=null)
            criteria.andTelephoneLike("%"+condition.getTel()+"%");
        List<Users> list=usersMapper.selectByExample(usersExample);
        return new PageInfo<>(list);
    }

    @Override
    public boolean checkUname(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0")); //保证房东用户
        criteria.andNameEqualTo(name);
        List<Users> list=usersMapper.selectByExample(usersExample);
        //如果当集合为null说明没有查询到记录,用户名可用
        if(list!=null&&list.size()!=0){
            return false;
        }
        else
        return true;
    }

    @Override
    public int addUser(Users users) {
        //出于系统用户安全考虑_对密码进行加密码
         //使用MD5工具类对密码加密
        String newPassword=MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        //设置户东用户
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String usernmae, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0")); //保证房东用户
        criteria.andNameEqualTo(usernmae);  //用户名
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));  //密码

        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list!=null&& list.size()>0)
        {
            return list.get(0);  //返回登入的人
        }
        return null;
    }
}
