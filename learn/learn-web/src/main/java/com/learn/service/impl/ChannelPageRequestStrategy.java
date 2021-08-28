package com.learn.service.impl;

import com.learn.service.ILearnWebRequestStrategy;
import com.learn.util.WebRequestParam;
import org.springframework.stereotype.Service;

/**
 * 栏目请求处理策略
 */
@Service("channelPageRequestStrategy")
public class ChannelPageRequestStrategy implements ILearnWebRequestStrategy {
    @Override
    public String handleRequest(WebRequestParam webRequestParam) {
        return "channel";
    }
}
