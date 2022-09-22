package com.steven.maven.archetype.application.service.user.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.maven.archetype.application.service.user.UserInfoService;
import com.steven.maven.archetype.domain.entity.user.UserInfoEntity;
import com.steven.maven.archetype.domain.mapper.user.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
}
