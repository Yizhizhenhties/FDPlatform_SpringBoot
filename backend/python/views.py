import base64
import imp
from io import BytesIO
from nn_backend import *
import base64
import json
import redis as redis
import time
import requests as req
import datetime
from PIL import Image

processor = Backend()

RedisCache = redis.StrictRedis(host="localhost", port=6379, db=0)

# the queue of expect to detect
IMAGE_QUEUE = "imageQueue"
# slice size every foreach
BATCH_SIZE = 32
# server sleep when queue>0
SERVER_SLEEP = 0.1
# server sleep when queue=0
SERVER_SLEEP_IDLE = 0.5

def image_to_base64(image: Image.Image, fmt='png'):
    output_buffer = BytesIO()
    image.save(output_buffer, format=fmt)
    byte_data = output_buffer.getvalue()
    base64_str = base64.b64encode(byte_data).decode('utf-8')
    # return f'data:image/{fmt};base64,' + base64_str
    return base64_str

def detect_process():
    print("starting detect")
    while True:
        # 从redis中获取预测图像队列
        item = RedisCache.lpop(IMAGE_QUEUE)
        if item is None:
            time.sleep(SERVER_SLEEP)
            continue
        # detect image 识别图片
        item = json.loads(item)
        image_key = item.get("imageKey")
        myFiles = item.get("imageUrls")
        now_time = datetime.datetime.now()
        base64_img_list = []
        for myFile in myFiles:
            img = Image.open(BytesIO(base64.b64decode(myFile))).convert('RGB')
            is_fabric, is_normal, mask, heat = processor.process(img)
            src_img = image_to_base64(img)
            base64_img = image_to_base64(mask)
            heat = image_to_base64(heat)
            if not is_fabric:
                base64_img_list.append({
                    "is_fabric": False,
                    "src_img": src_img,
                    "pro_img": "此图片不为目标纺织物",
                    "is_normal": "此图片不为目标纺织物",
                    "time": now_time.strftime('%Y-%m-%d %H:%M:%S'),
                    "has_pro_img": False,
                    "heat": heat
                })
            else:
                base64_img_list.append({
                    "is_fabric": True,
                    "src_img": src_img,
                    "pro_img": '无缺陷部分' if is_normal else base64_img,
                    "is_normal": 'True' if is_normal else 'False',
                    "time": now_time.strftime('%Y-%m-%d %H:%M:%S'),
                    "has_pro_img": False if is_normal else True,
                    "heat": heat
                })
        result = {'errcode': 200, 'data': base64_img_list}
        RedisCache.hset(name=image_key, key="consultOut", value=json.dumps(result))
        time.sleep(SERVER_SLEEP)

if __name__ == '__main__':
    detect_process()



# @app.route("/getprocessoutput", methods=['POST'])
# def getProcessOutput():
#     myFiles = request.files.getlist('file')
#     if not myFiles:
#         return Response(json.dumps({"errcode": 500,
#                                     "errmsg": "NoFiles"}), mimetype='application/json')
#     now_time = datetime.datetime.now()
#     base64_img_list = []
#     for myFile in myFiles:
#         img = Image.open(myFile).convert('RGB')
#         is_fabric, is_normal, mask, heat = processor.process(img)
#         src_img = image_to_base64(img)
#         base64_img = image_to_base64(mask)
#         heat = image_to_base64(heat)
#         if not is_fabric:
#             base64_img_list.append({
#                 "is_fabric": False,
#                 "src_img": src_img,
#                 "pro_img": "此图片不为目标纺织物",
#                 "is_normal": "此图片不为目标纺织物",
#                 "time": now_time.strftime('%Y-%m-%d %H:%M:%S'),
#                 "has_pro_img": False,
#                 "heat": heat
#             })
#         else:
#             base64_img_list.append({
#                 "is_fabric": True,
#                 "src_img": src_img,
#                 "pro_img": '无缺陷部分' if is_normal else base64_img,
#                 "is_normal": 'True' if is_normal else 'False',
#                 "time": now_time.strftime('%Y-%m-%d %H:%M:%S'),
#                 "has_pro_img": False if is_normal else True,
#                 "heat": heat
#             })
#     result = {'errcode': 200, 'data': base64_img_list}
#     return Response(json.dumps(result), mimetype='application/json')
