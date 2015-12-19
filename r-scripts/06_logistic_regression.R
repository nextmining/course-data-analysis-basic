### 로지스틱 회귀분석(Logistic Regression)
# http://rstudio-pubs-static.s3.amazonaws.com/14459_af8466cb23844215b74c03a471bf8e3c.html
# 예측하고자하는 분류가 0, 1의 두개가 아닌, 여러개가 될수 있는 경우 (0, 1, 2같은) 
# Multinomial Logistic Regression을 사용한다.

library(nnet)

# Sepal Length, Width: 꽃받침의 길이, 두께
# Petal Length, Width: 꽃잎의 길이, 두께
# Species: Iris 붓꽃의 종류
#   setosa
#   virginica
#   versicolor
names(iris)
iris

?multinom
m <- multinom(Species ~ ., data = iris)
m

## 작성한 모델이 주어진 훈련데이터를 어떻게 분류하고 있는지는 fitted()를 사용해 구할 수 있다.
#head(fitted(m))
fitted(m)

a <- apply(fitted(m), 1, max)
ifelse(a == 1, "setosa", ifelse(a == 2, "versicolor", "virginica"))
predict(m)

## 테스트
predict(m, newdata = iris[c(1, 51, 101), ], type = "class")
predict(m, newdata = iris[c(1, 51, 101), ], type = "probs")

## 모델 평가:
# 모델의 정확도를 확인하기 위해 예측된 Species와 실제 Species를 비교한다.
predicted <- predict(m, newdata = iris)
sum(predicted == iris$Species)/NROW(iris)
# 분할표로 확인
xtabs(~predicted + iris$Species)
