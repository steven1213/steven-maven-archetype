package com.steven.maven.archetype.application.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.maven.archetype.domain.entity.app.AppEntity;
import com.steven.maven.archetype.model.req.AppGeneratorReq;
import com.steven.maven.archetype.model.resp.AppResp;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
public interface AppService extends IService<AppEntity> {

    /**
     * 生成appId  & appSecret.
     *
     * @return AppResp
     */
    AppResp generator(AppGeneratorReq generatorReq);

    /**
     * appSecret 重置
     *
     * @param appId -
     * @return AppResp
     */
    AppResp appSecretReset(String appId);
}
