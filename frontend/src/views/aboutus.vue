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
        å³äºæä»¬
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
      Q1: "ð¤ <b>Who we are? æä»¬æ¯è°</b>",
      Text1: "ðââï¸ æä»¬æ¯<b>æ·±å³æä¸ç¥åå¤§å­¦</b>çå ä½ä¸ç¥åå­¦çç»æçTeam",
      Q2: "ð¤ <b>What's this? è¿ä¸ªå¹³å°æ¯å¹²ä»ä¹ç</b>",
      Text2: "ðââï¸ <b>çººç»ç©ç¼ºé·æ£æµ</b>ä¸å¼å§æ¯ä½ä¸ºæä»¬åå <b>æåæ°æ¯èµ</b>æç³æ¥çé¡¹ç®ï¼åæ¥å ä¸ºä¸å¯æå(ð¤¦ââï¸è¢«å·äº)èæç½®ï¼ç°å¨ä½ä¸º<b>è¯¾ç¨ä½ä¸</b>éæ°å®ç°"
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