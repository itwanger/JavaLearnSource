<template>
  <div class="app-container">
    <div class="styleof-inlineblock left-part">
      <div class="filter-container">
        <div>
          <el-input v-model="listQuery.siteName" :placeholder="$t('columns.columnName')" style="width: 83.5%;" class="filter-item" @keyup.enter.native="handleFilter" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
            {{ $t('operation.search') }}
          </el-button>
        </div>
        <div class="text-right">
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-plus" @click="handleCreate">
            {{ $t('columns.createRootColumn') }}
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-plus" @click="handleCreate">
            {{ $t('columns.createChildColumn') }}
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
            {{ $t('operation.edit') }}
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="handleCreate">
            {{ $t('operation.delete') }}
          </el-button>
        </div>
        <div>
          <el-tree :data="treeData" :props="treeDefaultProps" @node-click="handleNodeClick" />
        </div>
      </div>
    </div>
    <div class="styleof-inlineblock right-part">
      <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;" @sort-change="sortChange">
        <el-table-column :label="$t('columns.id')" prop="siteId" sortable="custom" align="center" width="130">
          <!--:class-name="getSortClass('id')"-->
          <template slot-scope="{row}">
            <span>{{ row.siteId }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('columns.columnName')" width="200px">
          <template slot-scope="{row}">
            <span>{{ row.siteName }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('columns.columnDesc')">
          <template slot-scope="{row}">
            <span>{{ row.siteDesc }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('operation.actions')" align="center" width="230px" class-name="small-padding fixed-width">
          <template slot-scope="{row,$index}">
            <el-button type="primary" size="mini" @click="handleUpdate(row)">
              {{ $t('operation.edit') }}
            </el-button>
            <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
              {{ $t('operation.delete') }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="siteDataModel" label-position="left" label-width="100px" style="width: 800px; margin-left:50px;">
        <el-form-item :label="$t('columns.siteName')" prop="siteName">
          <el-input v-model="siteDataModel.siteName" maxlength="200" />
        </el-form-item>
        <el-form-item :label="$t('columns.domain')" prop="domain">
          <el-input v-model="siteDataModel.domain" maxlength="200" />
        </el-form-item>
        <el-form-item :label="$t('columns.templateGroup')" prop="telName">
          <el-select v-model="siteDataModel.telName" class="filter-item" :placeholder="$t('operation.pleaseSelect')">
            <el-option v-for="item in templateSolutions" :key="item.key" :label="item.display_name" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('columns.staticDir')" prop="staticDir">
          <el-input v-model="siteDataModel.staticDir" maxlength="200" />
        </el-form-item>
        <el-form-item :label="$t('columns.siteDesc')">
          <el-input v-model="siteDataModel.siteDesc" :autosize="{ minRows: 4, maxRows: 6}" type="textarea" placeholder="Please input" maxlength="1000" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          {{ $t('operation.cancel') }}
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          {{ $t('operation.save') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getSiteList, addSite, updateSite, deleteSite } from '@/api/site'
import { getAllColumns } from '@/api/column'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { emptyChecker } from '@/utils/validate'
// import axios from 'axios'
import qs from 'qs'

const templateSolutions = [
  { key: '1', display_name: '方案1' },
  { key: '2', display_name: '方案2' },
  { key: '3', display_name: '方案3' },
  { key: '4', display_name: '方案4' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const templateSolutionsKeyValue = templateSolutions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'SitesManagement',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    templateSolutionFilter(type) {
      return templateSolutionsKeyValue[type]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,

      // 分页查询对象
      listQuery: {
        page: 1,
        pageSize: 20,
        siteName: undefined,
        domain: undefined,
        sort: '+id'
      },
      importanceOptions: [1, 2, 3],
      templateSolutions,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      siteDataModel: {
        siteId: undefined,
        siteName: '',
        siteDesc: '',
        domain: '',
        telName: '',
        staticDir: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: this.$t('columns.editSite'),
        create: this.$t('columns.createSite')
      },
      rules: {
        siteName: [{ required: true, validator: emptyChecker, message: this.$t('columns.validNotEmptySiteName'), trigger: 'blur' }],
        domain: [{ required: true, validator: emptyChecker, message: this.$t('columns.validNotEmptyDomain'), trigger: 'blur' }],
        telName: [{ required: true, validator: emptyChecker, message: this.$t('columns.validNotTelName'), trigger: 'blur' }]
      },
      downloadLoading: false,
      treeData: null,
      treeDefaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  computed: {
    currentSiteId() {
      return this.$store.getters.siteManagement.currentSiteId
    }
  },
  created() {
    this.getList()
    this.getTreeData()
  },
  methods: {
    getList() {
      this.listLoading = true
      getSiteList(this.listQuery).then(response => {
        this.list = response.result.items
        this.total = response.result.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },

    getTreeData() {
      getAllColumns({ siteId: this.currentSiteId }).then(response => {
        this.treeData = response.result
      })
    },

    // 列表分页查询方法
    handleFilter() {
      this.listQuery.page = 1

      // Todo: 查询的时候先把条件都设置为undefined，因为接口暂时还没支持，当支持的时候可以删掉
      this.listQuery.siteName = undefined
      this.listQuery.domain = undefined
      this.listQuery.sort = undefined

      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: this.$t(),
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetSiteDataModel() {
      this.siteDataModel = {
        siteId: undefined,
        siteName: '',
        siteDesc: '',
        domain: '',
        telName: '',
        staticDir: ''
      }
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          // this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          // this.temp.author = 'vue-element-admin'
          const postData = qs.stringify(this.siteDataModel)
          addSite(postData).then(() => {
            this.handleFilter()
            this.dialogFormVisible = false
            this.resetSiteDataModel()
            this.$notify({
              title: this.$t('operation.success'),
              message: this.$t('operation.operationSuccessed'),
              type: 'success',
              duration: 2000
            })
          })

          // const tempddd = { 'siteName': '测试3-62', 'siteDesc': '', 'domain': 'www.ceshi6', 'telName': '3', 'staticDir': '' }

          // axios.post('/boot-api/api/site/insert', tempddd, { headers: { 'content-type': 'application/json' }}).then(res => {
          //   console.log('保存res', res)
          // }, rej => {
          //   console.log('保存失败rej', rej)
          // })
        }
      })
    },
    handleUpdate(row) {
      console.log('update row data----------', row)
      this.siteDataModel = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const postData = qs.stringify(this.siteDataModel)
          updateSite(postData).then(() => {
            const index = this.list.findIndex(v => v.siteId === this.siteDataModel.siteId)
            this.list.splice(index, 1, this.siteDataModel)
            this.dialogFormVisible = false
            this.resetSiteDataModel()
            this.$notify({
              title: this.$t('operation.success'),
              message: this.$t('operation.operationSuccessed'),
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row, index) {
      const that = this
      const confirmMes = that.$t('columns.deleteWarning')
      this.$confirm(confirmMes, that.$t('operation.systemInfo'), {
        confirmButtonText: that.$t('operation.confirm'),
        cancelButtonText: that.$t('operation.cancel'),
        type: 'warning'
      }).then(() => {
        deleteSite(row.siteId).then(() => {
          this.$notify({
            title: this.$t('operation.success'),
            message: this.$t('operation.deleteSuccessed'),
            type: 'success',
            duration: 2000
          })
          this.list.splice(index, 1)
        })
      })
    }
  }
}
</script>
<style scoped>
.left-part {
  width: 35%;
  padding-right: 10px;
}
.right-part {
  width: 65%;
  padding-left: 10px;
}
</style>
