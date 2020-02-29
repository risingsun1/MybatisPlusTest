package com.jtexplorer.service.impl;

import com.jtexplorer.entity.User;
import com.jtexplorer.mapper.UserMapper;
import com.jtexplorer.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
