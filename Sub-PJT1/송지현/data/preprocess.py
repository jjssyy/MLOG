import os
import csv
import numpy as np
import pandas as pd
import shutil
from PIL import Image
# import torch


# Req. 3-1	이미지 경로 및 캡션 불러오기

def get_path_caption():
    path = '.\\datasets\\captions.csv'
    captions = pd.read_csv(path, sep='|', encoding='utf-8')
    captions_images_list = captions.image_name
    captions_comment_list = captions[' comment']
    captions_dict = {}
    for i in range(len(captions)):
        name = captions.image_name[i]
        if name in captions_dict:
            captions_dict[name] += [captions[' comment'][i]]
        else:
            captions_dict[name] = [captions[' comment'][i]]
    return [captions_images_list, captions_comment_list, captions_dict]

# Req. 3-2	전체 데이터셋을 분리해 저장하기
def dataset_split_save():
    train_dataset_path = '.\\datasets\\train'
    val_dataset_path = '.\\datasets\\val'
    # os.makedirs('.\\datasets\\train', exist_ok=True)
    # os.makedirs('.\\datasets\\val', exist_ok=True)
    # images_list = os.listdir('.\\datasets\\images')
    # shuffled_indices = np.random.permutation(len(images_list)) 
    # val_set_size = int(len(images_list) * 0.2)
    # val_indices = shuffled_indices[:val_set_size]
    # train_indices = shuffled_indices[val_set_size:]
    # for train in train_indices:
    #     img = '.\\datasets\\images\\' + images_list[train]
    #     src = '.\\datasets\\train\\'
    #     shutil.copy(img,src)

    # for val in val_indices:
    #     img = '.\\datasets\\images\\' + images_list[val]
    #     src = '.\\datasets\\val\\'
    #     shutil.copy(img,src)

    return [train_dataset_path, val_dataset_path]

# Req. 3-3	저장된 데이터셋 불러오기
def get_data_file(dataset_path,img_captions):
    img_paths = os.listdir(dataset_path)
    caption = {}
    for img in img_paths:
        caption[img] = img_captions[img]
    return [img_paths, caption]

# Req. 3-4	데이터 샘플링
def sampling_data(img_paths, img_captions ,ratio):
    img_path = []
    caption = {}
    shuffled_indices = np.random.permutation(len(img_paths))
    sample_size = int(len(img_paths) * ratio)
    sample_data = shuffled_indices[:sample_size]
    for i in sample_data:
        img_path += [img_paths[i]]
    print('sample data : {}개  ratio: {}%'.format(sample_size, int(ratio * 100)))
    for img in img_path:
        caption[img] = img_captions[img]
    return [img_path, caption]
