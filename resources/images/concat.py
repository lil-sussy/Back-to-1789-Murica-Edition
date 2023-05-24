# import cv2 library
import cv2

# read the images
img1 = cv2.imread('hero_left_walk.png')
img2 = cv2.imread('hero_right_walk.png')

im_v = cv2.vconcat([img1, img2])

# show the output image
cv2.imwrite('hero_sprite_sheet.png', im_v)