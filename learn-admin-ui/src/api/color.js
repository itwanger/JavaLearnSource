import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/color/list',
    method:'get',
    params:params
  })
}
export function createColor(data) {
  return request({
    url:'/color/create',
    method:'post',
    data:data
  })
}




export function deleteColor(id) {
  return request({
    url:'/color/delete/'+id,
    method:'get',
  })
}

export function getColor(id) {
  return request({
    url:'/color/'+id,
    method:'get',
  })
}

export function updateColor(id,data) {
  return request({
    url:'/color/update/'+id,
    method:'post',
    data:data
  })
}

