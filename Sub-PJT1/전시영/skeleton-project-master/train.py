import config
from data import preprocess 
from utils import utils


# config 저장
utils.save_config()


# 이미지 경로 및 캡션 불러오기
img_paths, captions = preprocess.get_path_caption()


# 전체 데이터셋을 분리해 저장하기
train_dataset_path, val_dataset_path = preprocess.dataset_split_save(img_paths, captions)


# 저장된 데이터셋 불러오기
img_path, caption = preprocess.get_data_file('train',train_dataset_path)
# print(img_path[0])


# 데이터 샘플링
if config.do_sampling:
    img_paths, captions = preprocess.sampling_data(img_paths, captions, 0.1)

img = img_paths.values
cap = captions.values

# 이미지와 캡션 시각화 하기
utils.visualize_img_caption(img[0], cap[0])
