package com.learn.util;

/**
 * 文章发布状态
 */
public enum PostStatus {
    PUBLISHED("published"),
    DELETED("deleted"),
    DRAFT("draft");
    private String status;

    public String getStatus() {
        return status;
    }

    PostStatus(String status) {
        this.status = status;
    }
}