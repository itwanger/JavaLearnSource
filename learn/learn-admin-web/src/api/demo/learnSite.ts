import { defHttp } from '/@/utils/http/axios';
import { LearnSitePageParams, LearnSiteListGetResultModel } from './model/leanSite';

enum Api {
  LearnSiteList = '/site/queryPageable',
}

export const getLearnSiteListApi = (params: LearnSitePageParams) =>
  defHttp.get<LearnSiteListGetResultModel>({
    url: Api.LearnSiteList,
    params,
  });
