import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';
import { t } from '/@/hooks/web/useI18n';

const learnsite: AppRouteModule = {
  path: '/learnsite',
  name: 'LearnSite',
  component: LAYOUT,
  redirect: '/learnsite/site',
  meta: {
    orderNo: 2000,
    icon: 'ion:ios-paper-outline',
    title: t('routes.learnsite.learnsite'),
  },
  children: [
    {
      path: 'site',
      name: 'site',
      component: () => import('/@/views/demo/learnsite/site/index.vue'),
      meta: {
        // affix: true,
        title: t('routes.learnsite.site'),
      },
    },
    {
      path: 'termtaxonomy',
      name: 'termtaxonomy',
      component: () => import('/@/views/demo/learnsite/termtaxonomy/index.vue'),
      meta: {
        // affix: true,
        title: t('routes.learnsite.termtaxonomy'),
      },
    },
    {
      path: 'posts',
      name: 'posts',
      component: () => import('/@/views/demo/learnsite/posts/index.vue'),
      meta: {
        // affix: true,
        title: t('routes.learnsite.posts'),
      },
    },
    {
      path: 'comments',
      name: 'comments',
      component: () => import('/@/views/demo/learnsite/comments/index.vue'),
      meta: {
        // affix: true,
        title: t('routes.learnsite.comments'),
      },
    },
  ],
};

export default learnsite;
