package com.steven.maven.archetype.adatper.web.controller.app;

import com.steven.maven.archetype.application.service.app.AppService;
import com.steven.maven.archetype.infra.general.constant.Constants;
import com.steven.maven.archetype.infra.general.req.Req;
import com.steven.maven.archetype.infra.general.resp.Resp;
import com.steven.maven.archetype.model.req.AppGeneratorReq;
import com.steven.maven.archetype.model.req.AppSecretResetReq;
import com.steven.maven.archetype.model.resp.AppResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: steven.cao1213
 * @version: 1.8.
 */
@Api(tags = "开放应用")
@RestController
@RequestMapping(Constants.ADAPTER_WEB + "/app")
public class AppController {

    @Resource
    private AppService appService;

    @ApiOperation("生成appId和appSecret")
    @PostMapping("/generator")
    public Resp<AppResp> generatorAppIdAndAppSecret(@RequestBody @Validated Req<AppGeneratorReq> generatorReq) {
        return Resp.success(appService.generator(generatorReq.getBody()));
    }

    @ApiOperation("appSecret重置")
    @PostMapping("/app-secret/reset")
    public Resp<AppResp> resetAppSecret(@RequestBody @Validated Req<AppSecretResetReq> resetReq) {
        return Resp.success(appService.appSecretReset(resetReq.getBody().getAppId()));
    }
}
