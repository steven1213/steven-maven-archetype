package com.steven.maven.archetype.application.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.maven.archetype.application.service.app.AppService;
import com.steven.maven.archetype.domain.entity.app.AppEntity;
import com.steven.maven.archetype.domain.mapper.app.AppMapper;
import com.steven.maven.archetype.infra.exception.BizException;
import com.steven.maven.archetype.infra.general.types.ResultCode;
import com.steven.maven.archetype.infra.general.utils.AppIdSecretUtils;
import com.steven.maven.archetype.model.req.AppGeneratorReq;
import com.steven.maven.archetype.model.resp.AppResp;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, AppEntity> implements AppService {
    @Override
    public AppResp generator(AppGeneratorReq generatorReq) {
        String appId = AppIdSecretUtils.appIdGenerator();
        String appSecret = AppIdSecretUtils.appSecretGenerator(appId);
        AppEntity appEntity = AppEntity.builder()
                .appSecret(appSecret)
                .appId(appId)
                .desc(generatorReq.getDesc())
                .appName(generatorReq.getAppName())
                .build();
        this.save(appEntity);
        return AppResp.builder().appId(appId).appSecret(appSecret).build();
    }

    @Override
    public AppResp appSecretReset(String appId) {
        // 查询appId是否存在
        AppEntity dbRecord = this.getOne(new LambdaQueryWrapper<AppEntity>().eq(AppEntity::getAppId, appId));
        if (Objects.isNull(dbRecord)) {
            throw new BizException(ResultCode.FAILURE.getKey(), "appId不存在");
        }
        String newAppSecret = AppIdSecretUtils.appSecretGenerator(appId);
        dbRecord.setAppSecret(newAppSecret);
        this.updateById(dbRecord);
        return AppResp.builder().appId(appId).appSecret(newAppSecret).build();
    }
}
