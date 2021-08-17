import { BasicPageParams, BasicFetchResult } from '/@/api/model/baseModel';
/**
 * @description: Request list interface parameters
 */

export type LearnSiteParams = {
  LearnSiteName?: string;
  status?: string;
};

export type LearnSitePageParams = BasicPageParams & LearnSiteParams;

export interface LearnSiteListItem {
  siteId: number;
  siteName: string;
  siteDesc: string;
  domain: string;
  telName: string;
  staticDir: number;
}

/**
 * @description: Request list return value
 */
export type LearnSiteListGetResultModel = BasicFetchResult<LearnSiteListItem>;
