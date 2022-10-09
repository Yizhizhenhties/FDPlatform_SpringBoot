<template>
  <div class="helper">
    <t-row style="height: 4%">
      <div>
        <img
          slot="helper_img"
          class="helper_img"
          src="../assets/helper.png"
          alt="helper_img"
          style="margin-left: 40px; width: 24px"
        />
      </div>
      <span
        style="
          font-family: cursive;
          font-size: 24px;
          font-weight: bold;
          margin-left: 10px;
        "
      >
        帮助文档
      </span>
    </t-row>
    <t-row style="height: 5%" />
    <div
      v-html="Q1"
      style="font-size: 24px; margin-left: 40px; margin-top: 10px"
    ></div>
    <div
      v-html="Text1"
      style="font-size: 18px; margin-left: 60px; margin-top: 20px"
    ></div>
    <div
      style="margin-top: 10px;text-align:center"
    >
      <img class="showimg"
          src="../assets/showimg.png"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "helper",
  components: {},
  data() {
    return {
       Q1: "🤔 <b>How it works? 检测原理是什么</b>",
       Text1: "🙋‍♂️ 异常检测是利用人工智能深度学习技术中名为<b>Ganomaly</b>的模型实现，并通过<b>语义分割</b>技术将缺陷部分展示",
    };
  },
  methods: {},
};
</script>

<style>
.helper {
  padding: 25px 75px;
  background: #ffffff;
  height: 100%;
}
</style>