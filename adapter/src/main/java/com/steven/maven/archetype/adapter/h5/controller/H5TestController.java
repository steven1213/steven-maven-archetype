package com.steven.maven.archetype.adapter.h5.controller;

import com.steven.maven.archetype.infra.general.constant.Constants;
import com.steven.maven.archetype.infra.general.resp.Resp;
import com.steven.maven.archetype.infra.log.opt.OperationLog;
import com.steven.maven.archetype.infra.log.sys.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: steven.cao
 * @date: 2022/9/22.
 * @version: 1.8.
 */
@Api(tags = "h5请求")
@RestController
@RequestMapping(Constants.ADAPTER_WEB + "/h5")
public class H5TestController {

    @ApiOperation("h5测试接口")
    @GetMapping("/hello")
    @OperationLog(desc = "测试请求")
    @SysLog(printReturn = false)
    public Resp<String> helloH5() {
        return Resp.success("hello,h5");
    }
}
