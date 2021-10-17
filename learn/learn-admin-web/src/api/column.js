import request from '@/utils/request-to-server'

export function getAllColumns(params) {
  return request({
    url: `/termTaxonomy/getPyParentId`,
    method: 'get',
    params
  })
}

