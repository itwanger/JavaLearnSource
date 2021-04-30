<template> 
  <el-card class="form-container" shadow="never">
    <el-form :model="color" :rules="rules" ref="colorFrom" label-width="150px">
      <el-form-item label="名称：" prop="name">
        <el-input v-model="color.name"></el-input>
      </el-form-item>
      <el-form-item label="色号：" prop="code">
        <el-input v-model="color.code"></el-input>
      </el-form-item>
      <el-form-item label="是否推荐：">
        <el-radio-group v-model="color.recommend">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否立体：">
        <el-radio-group v-model="color.depth">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
       <el-form-item label="一级分类/二级分类：" prop="oneGroupName">
            <el-cascader
              v-model="groupNames"
              :options="groupOptions"
              @change="handleGroupChange"
             >
              </el-cascader>
        </el-form-item>  
      <el-form-item label="图片：" prop="picUrls">
        <single-upload v-model="color.picUrls"></single-upload>
      </el-form-item>
      <el-form-item label="是否流行：">
        <el-radio-group v-model="color.popular">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="颜色：" prop="colorValue">
        <el-input v-model="color.colorValue" ></el-input>
      </el-form-item>
       <el-form-item label="R：" prop="rValue">
        <el-input v-model.number="color.rValue" ></el-input>
      </el-form-item>
       <el-form-item label="G：" prop="gValue">
        <el-input v-model.number="color.gValue" type="number"></el-input>
      </el-form-item>
       <el-form-item label="B：" prop="bValue">
        <el-input v-model.number="color.bValue" type="number"></el-input>
      </el-form-item>
      
      <el-form-item label="商品品牌：" prop="brandId">
            <el-select v-model="color.brandId" placeholder="请选择品牌" clearable>
              <el-option
                v-for="item in brandOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
       <el-form-item label="内墙/外墙：">
      <el-radio-group v-model="color.insideOutside">
          <el-radio :label="1">外墙</el-radio>
          <el-radio :label="0">内墙</el-radio>
        </el-radio-group>
      </el-form-item>
       <el-form-item label="色浆1（ML）：" prop="colorMill1">
        <el-input v-model.number="color.colorMill1" type="number"></el-input>
      </el-form-item>
         <el-form-item label="色浆2（ML）：" prop="colorMill2">
        <el-input v-model.number="color.colorMill2" type="number"></el-input>
      </el-form-item>
     
     <el-form-item label="色浆3（ML）：" prop="colorMill3">
        <el-input v-model.number="color.colorMill3" type="number"></el-input>
      </el-form-item>
       <el-form-item label="色浆4（ML）：" prop="colorMill4">
        <el-input v-model.number="color.colorMill4" type="number"></el-input>
      </el-form-item>
       <el-form-item label="色浆5（ML）：" prop="colorMill5">
        <el-input v-model.number="color.colorMill5" type="number"></el-input>
      </el-form-item>
       <el-form-item label="色浆6（ML）：" prop="colorMill6">
        <el-input v-model.number="color.colorMill6" type="number"></el-input>
      </el-form-item>
         <el-form-item label="色浆7（ML）：" prop="colorMill7">
        <el-input v-model.number="color.colorMill7" type="number"></el-input>
      </el-form-item>
      


    
      <el-form-item>
        <el-button type="primary" @click="onSubmit('colorFrom')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('colorFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import {createColor, getColor, updateColor} from '@/api/color'
  import SingleUpload from '@/components/Upload/singleUpload'
  import {fetchList as fetchBrandList} from '@/api/brand'
  const defaultColor={
    "code": "",
    "name": "",
    "style": '',
    "favorites": 0,
    "recommend": 0,
    "depth": 0,
    "twoGroupName": "",
    "oneGroupName": "",
    "picUrls": "",
    "popular": 0,
    "colorValue": "",
    "rValue": 0,
    "gValue": 0,
    "bValue": 0,
    "brandId": null,
    "insideOutside": 0,
    "unit": 0,
    "colorMill1": 0,
    "colorMill2": 0,
    "colorMill3": 0,
    "colorMill4": 0,
    "colorMill5": 0,
    "colorMill6": 0,
    "colorMill7": 0
   
    
  };
  export default {
    name: 'ColorDetail',
    components:{SingleUpload},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        color:Object.assign({}, defaultColor),
        groupNames:[],
        rules: {
          name: [
            {required: true, message: '请输入品牌名称', trigger: 'blur'}
          ],
          code: [
            {required: true, message: '请输入色号', trigger: 'blur'}
          ],
          oneGroupName: [
            {required: true, message: '请输入一级分类', trigger: 'blur'}
          ],
          colorValue: [
            {required: true, message: '请输入颜色', trigger: 'blur'}
          ],
          rValue: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          gValue: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          bValue: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],

          brandId: [
            {required: true, message: '请选择品牌', trigger: 'blur'}
          ],
          colorMill1: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill2: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill3: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill4: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill5: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill6: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ],
          colorMill7: [
            {type: 'number', message: '请输入数字', trigger: 'blur'}
          ]
        },
         groupOptions:[{
          value: '乳胶漆',
          label: '乳胶漆',
          children:[{
            value: '彩',
            label: '彩'
          },{
            value: '恒',
            label: '恒'
          },{
            value: '浅',
            label: '浅'
          },{
            value: '蕴',
            label: '蕴'
          },{
            value: '雅',
            label: '雅'
          }]  
        },{
          value: '外墙质感漆',
          label: '外墙质感漆'
        },{
          value: '木器漆',
          label: '木器漆'
        },{
          value: '艺术漆',
          label: '艺术漆',
            children:[{
            value: '丝绒',
            label: '丝绒'
          },{
            value: '岩彩',
            label: '岩彩'
          },{
            value: '流光',
            label: '流光'
          },{
            value: '浮光',
            label: '浮光'
          }]  
        }],
         brandOptions: []
      }
    },
    created() {
      if (this.isEdit) {
        getColor(this.$route.query.id).then(response => {
          this.color = response.data;
          this.groupNames.push( this.color.oneGroupName);
          this.groupNames.push( this.color.twoGroupName);
        });
      }else{
        this.color = Object.assign({},defaultColor);
      }
       this.getBrandList();
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('是否提交数据', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                updateColor(this.$route.query.id, this.color).then(response => {
                  this.$refs[formName].resetFields();
                  this.$message({
                    message: '修改成功',
                    type: 'success',
                    duration:1000
                  });
                  this.$router.back();
                });
              } else {

               
                createColor(this.color).then(response => {
                  this.$refs[formName].resetFields();
                  this.color = Object.assign({},defaultColor);
                  this.$message({
                    message: '提交成功',
                    type: 'success',
                    duration:1000
                  });
                });
              }
            });

          } else {
            this.$message({
              message: '验证失败',
              type: 'error',
              duration:1000
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.color = Object.assign({},defaultColor);
      },
      getBrandList() {
        fetchBrandList({pageNum: 1, pageSize: 100}).then(response => {
          this.brandOptions = [];
          let brandList = response.data.list;
          for (let i = 0; i < brandList.length; i++) {
            this.brandOptions.push({label: brandList[i].name, value: brandList[i].id});
          }
        });
      },
      handleGroupChange(){
        this.color.oneGroupName =  this.groupNames[0];
        this.color.twoGroupName = this.groupNames[1];
      }
    }
  }
</script>
<style>
</style>


