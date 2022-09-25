package com.steven.maven.archetype.adapter.web.controller;

import com.steven.maven.archetype.infra.general.constant.Constants;
import com.steven.maven.archetype.infra.general.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: steven.cao
 */
@Api(tags = "web请求")
@RestController
@RequestMapping(Constants.ADAPTER_WEB + "/test")
public class WebTestController {

    @ApiOperation("h5测试接口")
    @GetMapping("/hello")
    public Resp<String> helloWeb() {
        return Resp.success("hello,web");
    }
}
