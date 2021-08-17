import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import { Switch } from 'ant-design-vue';
import { setRoleStatus } from '/@/api/demo/system';
import { useMessage } from '/@/hooks/web/useMessage';

export const columns: BasicColumn[] = [
  {
    title: 'ID',
    dataIndex: 'siteId',
    fixed: 'left',
    width: 80,
  },
  {
    title: '站点名称',
    dataIndex: 'siteName',
  },
  {
    title: '站点介绍',
    dataIndex: 'siteDesc',
  },
  {
    title: '站点域名',
    dataIndex: 'domain',
  },
  {
    title: '模板方案',
    dataIndex: 'telName',
  },
  {
    title: '静态目录',
    dataIndex: 'staticDir',
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'roleNme',
    label: '角色名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'status',
    label: '状态',
    component: 'Select',
    componentProps: {
      options: [
        { label: '启用', value: '0' },
        { label: '停用', value: '1' },
      ],
    },
    colProps: { span: 8 },
  },
];

export const formSchema: FormSchema[] = [
  {
    field: 'siteName',
    label: '站点名称',
    required: true,
    component: 'Input',
  },
  {
    field: 'siteDesc',
    label: '站点介绍',
    component: 'Input',
  },
  {
    field: 'domain',
    label: '站点域名',
    component: 'Input',
  },
  {
    field: 'telName',
    component: 'Select',
    label: '模板方案',
    defaultValue: '1',
    colProps: {
      span: 8,
    },
    componentProps: {
      options: [
        {
          label: '模板1',
          value: '1',
          key: '1',
        },
        {
          label: '模板2',
          value: '2',
          key: '2',
        },
      ],
    },
  },
  {
    label: '静态目录',
    field: 'staticDir',
    component: 'Input',
  },
];
