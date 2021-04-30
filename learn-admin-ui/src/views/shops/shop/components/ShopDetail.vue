<template> 
  <el-card class="form-container" shadow="never">
    <el-form :model="shop" :rules="rules" ref="shopFrom" label-width="150px">
      <el-form-item label="店铺名称：" prop="name">
        <el-input v-if="isBrandEdit" v-model="shop.name"></el-input>
        <span v-else>{{ shop.name }}</span>
      </el-form-item>
      <el-form-item label="店铺地址：" prop="address">
        <el-input v-if="isBrandEdit" v-model="shop.address"></el-input>
        <span v-else>{{ shop.address }}</span>
      </el-form-item>
      <el-form-item label="店铺简介：" prop="brief">
        <el-input v-if="isBrandEdit" v-model="shop.brief" type="textarea"></el-input>
        <span v-else>{{ shop.brief }}</span>
      </el-form-item>
      <el-form-item label="营业执照注册号：" prop="businessLicense">
        <el-input v-if="isBrandEdit" v-model="shop.businessLicense"></el-input>
        <span v-else>{{ shop.businessLicense }}</span>
      </el-form-item>
      <el-form-item label="经度：" prop="latitude">
        <el-input v-if="isBrandEdit" v-model="shop.latitude"></el-input>
        <span v-else>{{ shop.latitude }}</span>
      </el-form-item>
      <el-form-item label="纬度：" prop="longitude">
        <el-input v-if="isBrandEdit" v-model="shop.longitude"></el-input>
        <span v-else>{{ shop.longitude }}</span>
      </el-form-item>
      <el-form-item label="法人：" prop="legalPersonName">
        <el-input v-if="isBrandEdit" v-model="shop.legalPersonName"></el-input>
        <span v-else>{{ shop.legalPersonName }}</span>
      </el-form-item>
      <el-form-item label="店铺logo：" prop="logo">
        <single-upload  v-if="isBrandEdit"  v-model="shop.logo"></single-upload>
        <img :src="shop.logo" />
      </el-form-item>
      <el-form-item label="店铺联系电话：" prop="mobile">
        <el-input v-if="isBrandEdit" v-model="shop.mobile"></el-input>
        <span v-else>{{ shop.mobile }}</span>
      </el-form-item>
      <el-form-item label="卖家承诺：" prop="promise">
        <el-input v-if="isBrandEdit" v-model="shop.promise"></el-input>
        <span v-else>{{ shop.promise }}</span>
      </el-form-item>
      <!-- <el-form-item label="卖家承诺：" prop="promise">
       <span>{{shop.promise}}</span>
     </el-form-item> -->
      <el-form-item label="店铺类型：" prop="shopType">
        <el-radio-group v-if="isBrandEdit" v-model="shop.shopType">
          <el-radio :label="1">服务中心</el-radio>
          <el-radio :label="0">店铺</el-radio>
        </el-radio-group>
        <span v-else>{{ shop.shopType * 1 === 1 ? '服务中心' : '店铺' }}</span>
      </el-form-item>
    <!--  <el-form-item label="会员id：" prop="umsMemberId">
        <el-input v-if="isBrandEdit" v-model="shop.umsMemberId"></el-input>
        <span v-else>{{ shop.umsMemberId }}</span>
      </el-form-item>-->
      <template v-if="!isBrandEdit">
        <el-form-item label="是否通过：">
          <el-radio-group v-model="shop.openStep">
            <el-radio :label="2">已通过</el-radio>
            <el-radio :label="3">拒审</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="shop.openStep * 1 === 3" label="拒审原因：" prop="backReason">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="shop.backReason"
          ></el-input>
        </el-form-item>
      </template>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('shopFrom')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('shopFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import {audit, getShop, updateShop} from '@/api/shops'
import SingleUpload from '@/components/Upload/singleUpload'

const defaultShop = {
  id: '',
  address: '',
  backReason: '',
  brief: '',
  businessLicense: '',
  configId: 0,
  delFlag: 0,
  geotableId: '',
  latitude: 0,
  legalPersonName: '',
  logo: '',
  longitude: 0,
  mobile: '',
  name: '',
  openStep: 2,
  ownerProxyId: 0,
  pmsShopWxconfigId: 0,
  promise: "string",
  qrcodeBg: "string",
  qrcodeUrl: "string",
  shopType: 0,
  umsMemberId: 0
};
export default {
  name: 'ShopDetail',
  components: {SingleUpload},
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      shop: Object.assign({}, defaultShop),
      rules: {
        backReason: [
          {required: this.isBrandEdit == true, message: '请输入拒审原因', trigger: 'blur'}
        ],
        name: [
          {required: this.isBrandEdit == true, message: '请输入店铺名称', trigger: 'blur'}
        ],
        address: [
          {required: this.isBrandEdit == true, message: '请输入店铺地址', trigger: 'blur'}
        ],
        businessLicense: [
          {required: this.isBrandEdit == true, message: '请输入营业执照注册号', trigger: 'blur'}
        ],
        umsMemberId: [
          {required: this.isBrandEdit == true, message: '请输入会员id', trigger: 'blur'}
        ]
      }
    }
  },
  computed: {
    isBrandEdit() {
      return this.$route.query.type === 'edit'
    }
  },
  created() {
    if (this.isEdit) {
      getShop(this.$route.query.id).then(response => {
        this.shop = response.data;
      });
    } else {
      this.shop = Object.assign({}, defaultShop);
    }
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

              updateShop(this.$route.query.id, this.shop).then(response => {
                this.$refs[formName].resetFields();
                this.$message({
                  message: this.isBrandEdit ? '编辑成功' : '审核成功！',
                  type: 'success',
                  duration: 1000
                });
                this.$router.back();
              });

            } else {

              audit(this.shop).then(response => {
                this.$refs[formName].resetFields();
                this.$message({
                  message: '审核成功！',
                  type: 'success',
                  duration: 1000
                });
                this.$router.back();
              });

            }
          });

        } else {
          this.$message({
            message: '验证失败',
            type: 'error',
            duration: 1000
          });
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.shop = Object.assign({}, defaultShop);
    }
  }
}
</script>
<style>
</style>


