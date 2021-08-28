package com.learn.service.impl;

import com.learn.service.ILearnWebRequestStrategy;
import com.learn.util.WebRequestParam;
import org.springframework.stereotype.Service;

/**
 * 首页请求处理策略
 */
@Service("indexPageRequestStrategy")
public class IndexPageRequestStrategy implements ILearnWebRequestStrategy {
    @Override
    public String handleRequest(WebRequestParam webRequestParam) {
        return "index";
    }
}
