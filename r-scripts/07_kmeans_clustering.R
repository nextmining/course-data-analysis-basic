##
# http://rischanlab.github.io/Kmeans.html
# http://www.rdatamining.com/examples/kmeans-clustering

names(iris)

# 최종적으로 분류할 라벨(Species)은 y변수에 할당하고 
# 설명변수 x에는 라벨(Species) 필드는 제외한다.
x <- iris[,-5]
y <- iris$Species

kc <- kmeans(x,3)
kc

# 클러스터링된 결과를 실제 분류라벨(y)과 비교해 보자.
table(y, kc$cluster)

plot(x[c("Sepal.Length", "Sepal.Width")], col = kc$cluster)
points(kc$centers[,c("Sepal.Length", "Sepal.Width")], col = 1:3, pch = 23, cex = 3)