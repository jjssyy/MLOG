import os
import csv
import numpy as np
import config
import pandas as pd
from sklearn.model_selection import train_test_split

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
def dataset_split_save(img_paths, captions):
    X_train, X_test, y_train, y_test = train_test_split(img_paths, captions, test_size=0.2, shuffle=True, random_state=1004)
    column = {'image':X_train, 'caption':y_train}
    train_dataset= pd.DataFrame(column)
    train_dataset_path = '.\\datasets\\train.csv'
    train_dataset.to_csv(train_dataset_path)

    column = {'image':X_test, 'caption':y_test}
    test_dataset = pd.DataFrame(column)
    val_dataset_path = '.\\datasets\\test.csv'
    test_dataset.to_csv(val_dataset_path)

    return train_dataset_path, val_dataset_path


# Req. 3-3	저장된 데이터셋 불러오기
def get_data_file(train_or_test, path):
    if train_or_test == 'train':
        dataset = pd.read_csv(path)
        img_path = dataset['image']
        caption = dataset['caption']
        return img_path, caption

    elif train_or_test == 'test':
        dataset = pd.read_csv(path)
        img_path = dataset['image']
        caption = dataset['caption']
        return img_path, caption


# Req. 3-4	데이터 샘플링
def sampling_data(img_paths, captions, percent):
    X_train, X_test, y_train, y_test = train_test_split(img_paths, captions, test_size=percent, shuffle=False)
    img_paths = X_test
    caption = y_test
    size = len(img_paths)
    print(size, str(percent*100)+'%')
    return img_paths, caption