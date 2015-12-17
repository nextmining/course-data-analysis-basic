
setwd("~/Documents/projects/course-data-analysis-basic/r-scripts")

##
# cars는 차의 속도(speed)와 급브레이크를 밟았을 때 멈추기까지 걸린 거리(dist)
# 회귀식: Yi = b0 + b1Xi, 즉 dist = b0 + b1*speed
cars <- read.csv('./data/sample/cars.csv')
cars

plot(dist ~ speed, data = cars)

?lm

out = lm(dist ~ speed, data = cars)
out

# 추정회귀식: dist = -17.5791 + 3.9324*speed
# 결정계수(coefficient of determination)인 R-squared은 모형의 설명력을 평가해 준다.
# 일반적으로 설명력이 없는 독립변수를 모형에 추가하더라도 R-squared 값이 조금씩 커지므로
# 조심할 것.
summary(out)

# 잔차는 관찰치와 추정치의 차이로 잘 적합된 모형에서 나온 잔차는 정규분포를 따라야 하고,
# 분산이 일정하고, 특별한 추세를 보이지 않아야 함.
# 잔차는 중요한 정보를 가지고 있어서는 안되는 찌꺼기 정보로 잔차가 추세를 보인다면 모형에
# 포함되어야 할 정보가 빠졌다는 증거
par(mfrow = c(2,2))
plot(out)
