import request from '@/utils/request-to-server'

class SiteOperations {
  getSiteList(params) {
    // console.log('调用getSiteList方法请求后台数据，请求携带数据：', data)
    return request({
      url: `/api/site/queryPageable?_=${Math.random()}`,
      method: 'get',
      params
    })
  }

  getOneSite(id) {
    return request({
      url: `/api/site/getById`,
      method: 'get',
      params: {
        id
      }
    })
  }

  updateSite(data) {
    return request({
      url: '/api/site/update',
      method: 'post',
      data
    })
  }

  addSite(data) {
    return request({
      url: '/api/site/insert',
      method: 'post',
      data
    })
  }

  deleteSite(id) {
    return request({
      url: `/api/site/delete?_=${Math.random()}`,
      method: 'get',
      params: {
        siteId: id
      }
    })
  }
}

export default new SiteOperations()
