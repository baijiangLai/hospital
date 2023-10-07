<template>
  <!-- header -->
  <div>

  </div>
  <!-- footer -->
</template>

<script>
export default {

  data() {
    return {

    }
  },

  mounted() {
    // 得到url后面问号拼接的内容(查询字段)
    let token = this.$route.query.token
    let name = this.$route.query.name
    let openid = this.$route.query.openid

    // 调用父vue方法
    window.parent['loginCallback'](name, token, openid)
  }
}
</script>
