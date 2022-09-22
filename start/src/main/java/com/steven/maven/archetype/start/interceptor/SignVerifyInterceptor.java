package com.steven.maven.archetype.start.interceptor;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.steven.maven.archetype.application.service.app.AppService;
import com.steven.maven.archetype.domain.entity.app.AppEntity;
import com.steven.maven.archetype.infra.general.config.SpringContextUtils;
import com.steven.maven.archetype.infra.general.constant.Constants;
import com.steven.maven.archetype.infra.general.resp.Resp;
import com.steven.maven.archetype.infra.general.types.ResultCode;
import com.steven.maven.archetype.infra.general.types.SignModelEnums;
import com.steven.maven.archetype.infra.general.utils.ServletUtils;
import com.steven.maven.archetype.infra.general.utils.SignVerifyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Slf4j
@Component
public class SignVerifyInterceptor implements HandlerInterceptor {

    @Value("${api.sign.verify.enable:false}")
    private boolean signEnable;

    @Resource
    private AppService appService;

    /**
     * 允许请求时间与服务器时间差为15秒
     */
    private static final long MAX_ALLOW_TIME = 15 * 1000L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果未开启API签名验证则直接返回true,跳过后续验证
        if (!signEnable) {
            return true;
        }
        String appId = request.getHeader(Constants.APP_ID);
        String timestampStr = request.getHeader(Constants.TIMESTAMP);
        String nonce = request.getHeader(Constants.NONCE);
        String sign = request.getHeader(Constants.SIGN);
        String signModel = request.getHeader(Constants.SIGN_MODE);
        String apiVersion = request.getHeader(Constants.API_VERSION);

        // 验证header是否有参数缺失
        if (StringUtils.isBlank(appId)
                || StringUtils.isBlank(timestampStr)
                || StringUtils.isBlank(nonce)
                || StringUtils.isBlank(signModel)
                || StringUtils.isBlank(sign)) {
            // header 参数缺失
            ServletUtils.renderString(response, JSONUtil.toJsonStr(Resp.failure(ResultCode.FAILURE.getKey(), "参数缺失")));
            return false;
        }
        long currentTime = System.currentTimeMillis();
        if (Math.abs(currentTime - Long.parseLong(timestampStr)) > MAX_ALLOW_TIME) {
            // 请求时间与服务器时间不一致
            ServletUtils.renderString(response, JSONUtil.toJsonStr(Resp.failure(ResultCode.FAILURE.getKey(), "请求时间与服务器时间不一致")));
            return false;
        }

        SignModelEnums signModelEnum = SignModelEnums.getByCode(signModel);
        if (Objects.isNull(signModelEnum)) {
            // 签名方式不致
            ServletUtils.renderString(response, JSONUtil.toJsonStr(Resp.failure(ResultCode.FAILURE.getKey(), "缺少签名验证方式")));
            return false;
        }
        //TODO 查询与APPID 对应的 APPSecret 从redis中获取或是查询数
        AppEntity appEntity = appService.getOne(new LambdaQueryWrapper<AppEntity>().eq(AppEntity::getAppId, appId));
        if (Objects.isNull(appEntity)) {
            ServletUtils.renderString(response, JSONUtil.toJsonStr(Resp.failure(ResultCode.FAILURE.getKey(), "appId无效")));
            return false;
        }
        String appSecret = appEntity.getAppSecret();

        apiVersion = StringUtils.isBlank(apiVersion) ? Constants.API_VERSION_DEFAULT : apiVersion;
        boolean signVerifyResult = SignVerifyUtils.verify(appId, appSecret, timestampStr, apiVersion, nonce, sign, signModelEnum);
        if (!signVerifyResult) {
            // sign不一致
            ServletUtils.renderString(response, JSONUtil.toJsonStr(Resp.failure(ResultCode.FAILURE.getKey(), "签名不一致")));
            return false;
        }
        return true;
    }
}
