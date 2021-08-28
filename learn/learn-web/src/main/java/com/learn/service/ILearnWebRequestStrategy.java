package com.learn.service;

import com.learn.util.WebRequestParam;

/**
 * 前端请求处理策略
 */
public interface ILearnWebRequestStrategy {
    String handleRequest(WebRequestParam webRequestParam);

}
