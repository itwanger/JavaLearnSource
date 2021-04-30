import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/pmsShops/listPmsShops',
    method:'get',
    params:params
  })
}



export function audit(data) {
  return request({
    url:'/pmsShops/audit',
    method:'post',
    data:data
  })
}

export function deleteShop(id) {
  return request({
    url:'/pmsShops/delete/'+id,
    method:'get',
  })
}

export function getShop(id) {
  return request({
    url:'/pmsShops/getShop/'+id,
    method:'get',
  })
}

export function updateShop(id,data) {
  return request({
    url:'/pmsShops/update/'+id,
    method:'post',
    data:data
  })
}

export function listShop() {
  return request({
    url:'/pmsShops/listShop',
    method:'get'
  })
}

export function listUserCenter() {
  return request({
    url:'/pmsShops/listUserCenter',
    method:'get'
  })
}

export function getShopCount() {
  return request({
    url:'/pmsShops/getShopCount',
    method:'get'
  })
}