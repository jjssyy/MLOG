from datetime import datetime
import os
import matplotlib.pyplot as plt
import matplotlib.image as img
import numpy as np
import tensorflow as tf
import sys
sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))
import config

# Req. 2-2	세팅 값 저장
def save_config():
	caption_file_path = config.args.caption_file_path
	images_file_path = config.args.images_file_path
	


# Req. 4-1	이미지와 캡션 시각화
def visualize_img_caption(img_paths, caption):
	for img_path in img_paths:
		image = img.imread(config.args.images_file_path+'\\'+img_path)
		plt.imshow(image)
		plt.title(caption[img_path][0])
		plt.show()
