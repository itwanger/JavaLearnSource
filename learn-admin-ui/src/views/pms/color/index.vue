<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
        <div>
          <i class="el-icon-search"></i>
          <span>筛选搜索</span>
          <el-button
            style="float: right"
            @click="searchColorList()"
            type="primary"
            size="small">
            查询结果
          </el-button>
           <el-button
          style="float: right;margin-right: 15px"
          @click="handleResetSearch()"
          size="small">
          重置
        </el-button>
        </div>
        <div style="margin-top: 15px">
          <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
            <el-form-item label="色号：">
              <el-input style="width: 200px" v-model="listQuery.code" placeholder="输入色号"></el-input>
            </el-form-item>
            <el-form-item label="名称：">
              <el-input style="width: 200px" v-model="listQuery.name" placeholder="输入名称"></el-input>
            </el-form-item>
          </el-form>
        </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        @click="addColor()"
        size="mini">
        添加
      </el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="brandTable"  :cell-style="cellStyle"
                :data="list"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                v-loading="listLoading"
                border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column label="编号" width="100" align="center">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="名称" align="center">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="色号"  align="center">
          <template slot-scope="scope">{{scope.row.code}}</template>
        </el-table-column>
          <el-table-column label="一级分类" align="center">
          <template slot-scope="scope">{{scope.row.oneGroupName}}</template>
        </el-table-column>
        <el-table-column label="二级分类" align="center">
          <template slot-scope="scope">{{scope.row.twoGroupName}}</template>
        </el-table-column>
        <el-table-column label="颜色" align="center" >
          <template slot-scope="scope" >{{scope.row.colorValue}}</template>
        </el-table-column>
          <el-table-column label="图片" align="center" >
          <template slot-scope="scope" ></template>
        </el-table-column>
        <!-- <el-table-column label="R" align="center">
          <template slot-scope="scope">{{scope.row.rValue}}</template>
        </el-table-column>
        <el-table-column label="G" align="center">
          <template slot-scope="scope">{{scope.row.gValue}}</template>
        </el-table-column>
        <el-table-column label="B" align="center">
          <template slot-scope="scope">{{scope.row.bValue}}</template>
        </el-table-column>
        <el-table-column label="品牌Id" align="center">
          <template slot-scope="scope">{{scope.row.brandId}}</template>
        </el-table-column>-->
        <el-table-column label="色浆1（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill1}}</template>
        </el-table-column>
        <el-table-column label="色浆2（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill2}}</template>
        </el-table-column>
         <el-table-column label="色浆3（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill3}}</template>
        </el-table-column>
         <el-table-column label="色浆4（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill4}}</template>
        </el-table-column>
          <el-table-column label="色浆5（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill5}}</template>
        </el-table-column>
         <el-table-column label="色浆6（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill6}}</template>
        </el-table-column>
          <el-table-column label="色浆7（ML）" align="center">
          <template slot-scope="scope">{{scope.row.colorMill7}}</template>
        </el-table-column>



        <el-table-column label="操作" width="150" align="center" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleUpdate(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[5,10,15]"
        :current-page.sync="listQuery.pageNum"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import {fetchList, deleteColor} from '@/api/color'

  export default {
    name: 'colorList',
    data() {
      return {

        operateType: null,
        listQuery: {
          code: '',
          name: '',
          twoGroupName: '',
          oneGroupName: '',
          pageNum: 1,
          pageSize: 10
        },
        list: null,
        total: null,
        listLoading: true,
        multipleSelection: []
      }
    },
    created() {
      this.getList();
    },
    methods: {
        handleResetSearch() {
          this.listQuery.code='';
          this.listQuery.name='';
          this.listQuery = Object.assign({}, this.listQuery);
      },
       cellStyle(row,column,rowIndex,columnIndex){//根据报警级别显示颜色
          if(row.column.label== '颜色')  {
             return 'background:'+row.row.colorValue;
          }else if(row.column.label== '图片')  {
             return 'background-image:url('+row.row.picUrls+')';
          }

      }
,
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
          this.totalPage = response.data.totalPage;
          this.pageSize = response.data.pageSize;
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleUpdate(index, row) {
        this.$router.push({path: '/pms/updateColor', query: {id: row.id}})
      },
      handleDelete(index, row) {
        this.$confirm('是否要删除该品牌', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteColor(row.id).then(response => {
            this.$message({
              message: '删除成功',
              type: 'success',
              duration: 1000
            });
            this.getList();
          });
        });
      },
      getProductList(index, row) {
        console.log(index, row);
      },
      getProductCommentList(index, row) {
        console.log(index, row);
      },
      handleFactoryStatusChange(index, row) {
        var data = new URLSearchParams();
        data.append("ids", row.id);
        data.append("factoryStatus", row.factoryStatus);
        updateFactoryStatus(data).then(response => {
          this.$message({
            message: '修改成功',
            type: 'success',
            duration: 1000
          });
        }).catch(error => {
          if (row.factoryStatus === 0) {
            row.factoryStatus = 1;
          } else {
            row.factoryStatus = 0;
          }
        });
      },
      handleShowStatusChange(index, row) {
        let data = new URLSearchParams();
        ;
        data.append("ids", row.id);
        data.append("showStatus", row.showStatus);
        updateShowStatus(data).then(response => {
          this.$message({
            message: '修改成功',
            type: 'success',
            duration: 1000
          });
        }).catch(error => {
          if (row.showStatus === 0) {
            row.showStatus = 1;
          } else {
            row.showStatus = 0;
          }
        });
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      searchColorList() {
        this.listQuery.pageNum = 1;
        this.getList();
      },
      addColor() {
        this.$router.push({path: '/pms/addColor'})
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>


</style>


