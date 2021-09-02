import os
import csv
import numpy as np
import config
import pandas as pd

# Req. 3-1	이미지 경로 및 캡션 불러오기
def get_path_caption():
    # image_name| comment_number| comment
    # 1000092795.jpg| 0| Two young guys with shaggy hair look at their hands while hanging out in the yard .
    data= pd.read_csv(config.args.caption_file_path, encoding='cp949', error_bad_lines=False, sep='|')
    # print(data)
    img_paths = data['image_name']
    captions = data[' comment']
    return img_paths, captions


# Req. 3-2	전체 데이터셋을 분리해 저장하기
def dataset_split_save():
    pass


# Req. 3-3	저장된 데이터셋 불러오기
def get_data_file():
    pass


# Req. 3-4	데이터 샘플링
def sampling_data():
    pass