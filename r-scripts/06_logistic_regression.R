### 다항 로지스틱 회귀분석(Multinomial Logistic Regression)
# http://rstudio-pubs-static.s3.amazonaws.com/14459_af8466cb23844215b74c03a471bf8e3c.html
# 예측하고자하는 분류가 0, 1의 두개가 아닌, 여러개가 될수 있는 경우 (0, 1, 2같은) 
# Multinomial Logistic Regression을 사용한다.

library(nnet)
iris
m <- multinom(Species ~ ., data = iris)

m

# 작성한 모델이 주어진 훈련데이터를 어떻게 분류하고 있는지는 fitted()를 사용해 구할 수 있다.
head(fitted(m))