<template>
  <div class="output">
    <t-row style="height: 4%">
      <div>
        <img
          slot="output_img"
          class="output_img"
          src="../assets/output.png"
          alt="output_img"
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
        η»ζζ₯η
      </span>
    </t-row>
    <t-row style="height: 5%" />
    <t-row style="height: 91%">
      <t-table
        :data="data"
        :columns="columns"
        :rowKey="rowKey"
        :size="size"
        :loading="loading"
        :rowspanAndColspan="rowspanAndColspan"
        :maxHeight="'100%'"
        style="height: 100%"
        bordered
      >
        <div slot="loading" class="t-table--loading-message">
          π θ―·θεΏη­εΎη»ζ π
        </div>
        <template #srcimg="{ row }">
          <img :src="row.srcimg" style="height: 40px" />
        </template>
        <template #path="{ row }">
          <img v-if="row.has_pro_img" :src="row.path" style="height: 40px" />
          <span v-else>{{ row.path }}</span>
        </template>
        <template #report="{ row }">
          <t-button @click="visible = true">{{ row.report }}</t-button>
        </template>
      </t-table>
    </t-row>
    <t-dialog
      :visible.sync="visible"
      :footer="false"
      :header="false"
      :width="'80%'"
      placement="center"
    >
      <t-row>
        <div>
          <img
            slot="reports_img"
            class="reports_img"
            src="../assets/reports.png"
            alt="reports_img"
            style="margin-left: 40px; width: 24px"
          />
        </div>
        <span
          style="
            font-family: cursive;
            font-size: 24px;
            margin-left: 10px;
            color: black;
          "
        >
          ζ£ζ΅ζ₯ε
        </span>
        <t-button
          theme="default"
          variant="text"
          style="margin-left: auto; color: cornflowerblue; margin-right: 20px"
          @click="visible1 = true"
          :disabled="true"
          >ε―Ήζ£ζ΅ζ₯εζηι?οΌηΉε»θΏιει¦οΌ</t-button
        >
      </t-row>
      <t-divider />
      <t-row>
        <h2 style="color: black; margin-left: 40px">ζ£ζ΅ζζοΌ</h2>
      </t-row>
      <t-row class="swiper-container">
        <swiper :options="swiperOption" class="swiper">
          <swiper-slide
            v-for="(item, index) in pro_imgList"
            :key="index"
            class="swiper-slide"
          >
            <img
              :src="item"
              style="
                background-position: 50%;
                background-size: cover;
                width: 100%;
              "
            />
          </swiper-slide>
          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </t-row>
      <t-divider />
      <t-row>
        <t-col :span="2">
          <h2 style="color: black; margin-left: 40px">ζ£ζ΅ζ»ζ°οΌ</h2>
        </t-col>
        <t-col :span="2">
          <h3 style="color: black; margin-left: 40px">{{ num_of_pro }}εΌ </h3>
        </t-col>
        <t-col :span="2">
          <h2 style="color: black; margin-left: 40px">εζ ΌδΊ§εζ°ιοΌ</h2>
        </t-col>
        <t-col :span="2">
          <h3 style="color: black; margin-left: 40px">{{ num_of_true }}εΌ </h3>
        </t-col>
        <t-col :span="2">
          <h2 style="color: black; margin-left: 40px">εζ ΌηοΌ</h2>
        </t-col>
        <t-col :span="2">
          <h3 style="color: black; margin-left: 40px">{{ rate }}%</h3>
        </t-col>
      </t-row>
      <t-divider />
    </t-dialog>
    <t-dialog
      :visible.sync="visible1"
      theme="warning"
      header="ζδΊ€ει¦"
      :confirmBtn="confirmBtn"
      :onConfirm="onConfirm"
      :width="'60%'"
      placement="center"
    >
      <h3 style="color: black">εΈΈθ§ι?ι’οΌ</h3>
      <t-checkbox-group
        v-model="checked"
        :options="['ηΊΊη»εθ―ε«ιθ――', 'ηΌΊι·ι¨δ½θ―ε«ιθ――', 'ηΌΊι·ι¨δ½ε?δ½δΈζΈζ°']"
      ></t-checkbox-group>
      <t-divider />
      <h3 style="color: black">ζε­ζθΏ°οΌ</h3>
      <t-textarea v-model="textdescription" placeholder="θ―·θΎε₯εε?Ή" />
    </t-dialog>
  </div>
</template>

<script>
export default {
  name: "Output",
  components: {},
  data() {
    return {
      visible: false,
      visible1: false,
      imgList: [],
      pro_imgList: [],
      loading: true,
      data: [],
      checked: [],
      columns: [
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "id",
          title: "ηΌε·",
        },
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "srcimg",
          title: "εεΎ",
        },
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "processdate",
          title: "ζ£ζ΅ζ₯ζ",
        },
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "processoutput",
          title: "ζ£ζ΅η»ζ",
        },
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "path",
          title: "ηΌΊι·ι¨δ½",
        },
        {
          align: "center",
          width: "100",
          minWidth: "100",
          ellipsis: true,
          colKey: "report",
          title: "ζ£ζ΅ζ₯ε",
        },
      ],
      textdescription: "",
      confirmBtn: "ζδΊ€ει¦",
      rowKey: "id",
      size: "small",
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true,
        },
        pagination: {
          el: ".swiper-pagination",
        },
      },
      num_of_pro: 0,
      num_of_true: 0,
      rate: 0,
    };
  },
  methods: {
    getParams() {
      let data = this.$route.params.data;
      this.imgList = data;
      var imgfilelist = new FormData();
      this.imgList.forEach((value, index) => {
        imgfilelist.append("file", value.file);
      });
      if(sessionStorage.getItem('username')) {
        imgfilelist.append("username", sessionStorage.getItem('username'))
      }
      this.$http
        .post("/api/processor/process/", imgfilelist, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((response) => {
          console.log(response);
          if (response.data.errcode === 200) {
            for (let i = 0; i < response.data.data.length; i++) {
              if (response.data.data[i].is_fabric) {
                this.pro_imgList.push('data:image/png;base64,' + response.data.data[i].heat);
              }
              if (response.data.data[i].is_normal === "True") {
                this.num_of_true += 1;
              }
              var prefix = response.data.data[i].has_pro_img ? 'data:image/png;base64,' : ''
              this.data.push({
                id: i,
                srcimg: 'data:image/png;base64,' + response.data.data[i].src_img,
                processdate: response.data.data[i].time,
                processoutput: response.data.data[i].is_normal,
                path: prefix + response.data.data[i].pro_img,
                report: "ζ₯ηζ₯ε",
                has_pro_img: response.data.data[i].has_pro_img,
              });
            }
            this.num_of_pro = this.pro_imgList.length;
            this.rate =
              (
                parseFloat(this.num_of_true) / parseFloat(this.num_of_pro)
              ).toFixed(2) * 100;
            this.loading = false;
          } else {
            this.$message.error("ζε‘ε¨θΏζ₯ε€±θ΄₯");
          }
        })
        .catch((error) => {
          this.$message.error("ζε‘ε¨θΏζ₯ε€±θ΄₯");
        });
    },
    getObjectURL(file) {
      var url = null;
      if (window.createObjectURL != undefined) {
        // basic
        url = window.createObjectURL(file);
      } else if (window.URL != undefined) {
        // mozilla(firefox)
        url = window.URL.createObjectURL(file);
      } else if (window.webkitURL != undefined) {
        // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
      }
      return url;
    },
    rowspanAndColspan({ col }) {
      if (col.colKey === "report") {
        return {
          rowspan: this.imgList.length,
        };
      }
    },
    onConfirm() {
      if (this.checked.length === 0 && this.textdescription === "") {
        this.$message.error("θ―·ιζ©εΈΈθ§ει¦ι?ι’ζε‘«εζε­ει¦");
        return;
      }
      this.confirmBtn = {
        content: "ζδΊ€δΈ­...",
        theme: "primary",
        loading: true,
      };
      //
      //θ―·ζ±
      //
      this.checked = [];
      this.textdescription = "";
      this.confirmBtn = "ζδΊ€ει¦";
      this.visible1 = false;
      this.$message.success("ζδΊ€ει¦ζεοΌζδ»¬δΌεζΆζ₯ηεΉΆθ§£ε³");
    },
  },
  created() {
    if (this.$route.params.data) {
      this.getParams();
    }
  },
};
</script>

<style lang="less">
.output {
  padding: 25px 75px;
  background: #ffffff;
  height: 100%;
}
.swiper-container {
  width: 100%;
  height: 200px;
}

.swiper {
  height: 100%;
  width: 100%;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 200px;
    height: 200px;
    text-align: center;
    font-weight: bold;
    background-position: center;
    background-size: cover;
  }

  .swiper-pagination {
    /deep/ .swiper-pagination-bullet.swiper-pagination-bullet-active {
      background-color: white;
    }
  }
}
</style>