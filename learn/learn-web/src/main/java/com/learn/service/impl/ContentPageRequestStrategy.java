package com.learn.service.impl;

import com.learn.service.ILearnWebRequestStrategy;
import com.learn.util.WebRequestParam;
import org.springframework.stereotype.Service;

/**
 * 内容请求处理策略
 */
@Service("contentPageRequestStrategy")
public class ContentPageRequestStrategy implements ILearnWebRequestStrategy {
    @Override
    public String handleRequest(WebRequestParam webRequestParam) {
        return "content";
    }
}
