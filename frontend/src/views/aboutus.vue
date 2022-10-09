<template>
  <div class="aboutus">
    <t-row style="height: 4%">
      <div>
        <img
          slot="aboutus_img"
          class="aboutus_img"
          src="../assets/aboutus.png"
          alt="aboutus_img"
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
        关于我们
      </span>
    </t-row>
    <t-row style="height: 5%" />
    <div
      v-html="Q1"
      style="font-size: 24px; margin-left: 40px; margin-top: 20px"
    ></div>
    <div
      v-html="Text1"
      style="font-size: 18px; margin-left: 60px; margin-top: 40px"
    ></div>
    <div
      v-html="Q2"
      style="font-size: 24px; margin-left: 40px; margin-top: 40px"
    ></div>
    <div
      v-html="Text2"
      style="font-size: 18px; margin-left: 60px; margin-top: 40px"
    ></div>
    
  </div>
</template>

<script>
export default {
  name: "aboutus",
  components: {},
  data() {
    return {
      Q1: "🤔 <b>Who we are? 我们是谁</b>",
      Text1: "🙋‍♂️ 我们是<b>深圳某不知名大学</b>的几位不知名学生组成的Team",
      Q2: "🤔 <b>What's this? 这个平台是干什么的</b>",
      Text2: "🙋‍♂️ <b>纺织物缺陷检测</b>一开始是作为我们参加<b>某创新比赛</b>所申报的项目，后来因为不可抗力(🤦‍♂️被刷了)而搁置，现在作为<b>课程作业</b>重新实现"
    };
  },
  methods: {},
};
</script>

<style>
.aboutus {
  padding: 25px 75px;
  background: #ffffff;
  height: 100%;
}
</style>