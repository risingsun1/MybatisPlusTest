package com.jtexplorer.service.impl;

import com.jtexplorer.entity.Role;
import com.jtexplorer.mapper.RoleMapper;
import com.jtexplorer.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表role 服务实现类
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
