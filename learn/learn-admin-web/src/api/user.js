import request from '@/utils/request-to-server'

export function login(data) {
  return request({
    url: '/api/users/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/users/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}

// #region 用户增删改查操作API

export function getUserList(data) {
  return request({
    url: `/api/users/queryPageable?_=${Math.random()}`,
    method: 'get',
    data
  })
}

export function getOneUser(id) {
  return request({
    url: `/api/users/getById`,
    method: 'get',
    data: {
      id
    }
  })
}

export function updateUser(data) {
  return request({
    url: '/api/users/update',
    method: 'post',
    data
  })
}

export function addUser(data) {
  return request({
    url: '/api/users/insert',
    method: 'post',
    data
  })
}

export function deleteUser(id) {
  return request({
    url: '/api/users/delete',
    method: 'get',
    data: {
      id
    }
  })
}

// #endregion
