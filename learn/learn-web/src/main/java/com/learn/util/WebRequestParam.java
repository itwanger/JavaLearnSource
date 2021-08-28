package com.learn.util;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebRequestParam {
    private Integer id;
    private Integer page;
    private String path;
    private HttpServletRequest request;
    private ModelMap model;
    private  HttpServletResponse response;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ModelMap getModel() {
        return model;
    }

    public void setModel(ModelMap model) {
        this.model = model;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    private  WebRequestParam (Builder builder){
        id = builder.id;
        page = builder.page;
        path = builder.path;
        request = builder.request;
        model = builder.model;
        response = builder.response;;
    }



    public static  class  Builder {
        private Integer id;
        private Integer page;
        private String path;
        private HttpServletRequest request;
        private ModelMap model;
        private  HttpServletResponse response;
        public Builder(){

        }
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder page(int page){
            this.page = page;
            return this;
        }
        public Builder path(String path){
            this.path = path;
            return this;
        }
        public Builder request(HttpServletRequest request){
            this.request = request;
            return this;
        }
        public Builder model(ModelMap model){
            this.model = model;
            return this;
        }
        public Builder response(HttpServletResponse response){
            this.response = response;
            return this;
        }
        public WebRequestParam build(){
            return new WebRequestParam(this);
        }
    }

}
