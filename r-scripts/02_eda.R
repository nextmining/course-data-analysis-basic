#################################
# EDA
#################################

### Dataset
# https://s3.amazonaws.com/udacity-hosted-downloads/ud651/pseudo_facebook.tsv 

setwd("~/Documents/projects/course-data-analysis-basic/r-scripts")
getwd()

list.files('./data')
pf <- read.csv('./data/pseudo_facebook.tsv', sep = '\t')
names(pf)
head(pf)

### Install required packages
install.packages('ggplot2')
library(ggplot2)

### Histogram of Users' Birthdays
qplot(x = dob_day, data = pf) +
  scale_x_discrete(breaks=1:31)

### Faceting
# facet_wrap(~variable)
# facet_grid(vertical~horizontal)
qplot(x = dob_day, data = pf) +
  scale_x_discrete(breaks=1:31) +
  facet_wrap(~dob_month, ncol = 3)

### Friend Count
qplot(x = friend_count, data = pf)
## Limiting the axes
qplot(x = friend_count, data = pf, xlim = c(0, 1000))
## Adjusting the Bin Width
qplot(x = friend_count, data = pf) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50))
## Faceting by gender
qplot(x = friend_count, data = pf) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50)) +
  facet_wrap(~gender)
## Omitting NA Gender Observations
qplot(x = friend_count, data = subset(pf, !is.na(gender)), 
      binwidth = 10) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50)) +
  facet_wrap(~gender)
## Statistics 'by' Gender <--- versus: summary(pf)
table(pf$gender)
by(pf$friend_count, pf$gender, summary)


### Tenure: How many days someone has been using Facebook.
## by Day
qplot(x = tenure, data = pf, binwidth = 30,
      color = I('black'), fill = I('#099DD9'))
## by Year
qplot(x = tenure/365, data = pf,
      xlab = 'Number of years using Facebook',
      ylab = 'Number of users in sample',
      color = I('black'), fill = I('#F79420')) +
  scale_x_continuous(breaks = seq(1, 7, 1), lim = c(0, 7))


### Transforming Data
### Add Log or Sqrt Scales to an Axis 
# 아래 friend_count의 경우 왼쪽으로 지나치게 치우쳐져 있는 걸 알 수 있다.(skewed)
# 예를 들어, Linear Regression 분석 등을 하는 경우, 정규분포를 가정하기 때문에
# 정규분포 형태로 데이터를 변환하는 기법에 대해서 설명한다.
qplot(x = friend_count, data = pf)
summary(pf$friend_count)

# log10(0) -> -Inf
summary(log10(pf$friend_count))

summary(log10(pf$friend_count + 1))

summary(sqrt(pf$friend_count))

### Install required packages
install.packages('gridExtra')
library(gridExtra)

p1 <- qplot(x = friend_count, data = pf)
p2 <- qplot(x = log10(pf$friend_count + 1), data = pf)
p2_1 <- qplot(x = friend_count, data = pf) +
  scale_x_log10()
p3 <- qplot(x = sqrt(pf$friend_count), data = pf)

# log normalization(p2)이 가장 정규분포(normal distribution)에 가까운 것을 알 수 있다.
grid.arrange(p1, p2, p3, ncol = 1)


### Frequency Polygons (before we had histograms)
qplot(x = friend_count, data = subset(pf, !is.na(gender)), 
      binwidth = 10) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50)) +
  facet_wrap(~gender)

qplot(x = friend_count, 
      data = subset(pf, !is.na(gender)), 
      binwidth = 10, geom ='freqpoly', color = gender) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50))

qplot(x = friend_count, y = ..count../sum(..count..),
      data = subset(pf, !is.na(gender)), 
      xlab = 'Friend Count',
      ylab = 'Proportion of Users with that friend count',
      binwidth = 10, geom ='freqpoly', color = gender) +
  scale_x_continuous(limits = c(0, 1000), breaks = seq(0, 1000, 50))

## Likes on the Web
# What's the www_like count for males?
# Which gender has more www_likes?

### Box Plots, Quartiles, and Friend Requests
# Box 상단 포인트들은 모두 outlier
# Box내의 horizontal line은 중간값(median)
by(pf$friend_count, pf$gender, summary)
qplot(x = gender, y = friend_count,
      data = subset(pf, !is.na(gender)),
      geom = 'boxplot')

qplot(x = gender, y = friend_count,
      data = subset(pf, !is.na(gender)),
      geom = 'boxplot') +
  coord_cartesian(ylim = c(0, 1000))

qplot(x = gender, y = friend_count,
      data = subset(pf, !is.na(gender)),
      geom = 'boxplot') +
  coord_cartesian(ylim = c(0, 250))


#####################################################
# Explore Tow Variables
#####################################################

### Scatter Plots: 
qplot(x = age, y = friend_count, data = pf)
qplot(age, friend_count, data = pf)
summary(pf$age)

ggplot(aes(x = age, y = friend_count), data = pf) +
  geom_point() +
  xlim(13, 90)
## Overplotting
ggplot(aes(x = age, y = friend_count), data = pf) +
  geom_point(alpha = 1/20) +
  xlim(13, 90)

ggplot(aes(x = age, y = friend_count), data = pf) +
  geom_jitter(alpha = 1/20) +
  xlim(13, 90)

## Coord_trans
ggplot(aes(x = age, y = friend_count), data = pf) +
  geom_point(alpha = 1/20) +
  xlim(13, 90) +
  coord_trans(y = 'sqrt')

### Conditional Means
install.packages('dplyr')
library(dplyr)

# filter()
# group_by()
# mutate()
# arrange()

age_groups <- group_by(pf, age)
# friend count by age
pf.fc_by_age <- summarise(age_groups,
                          friend_count_mean = mean(friend_count),
                          friend_count_median = median(friend_count),
                          n = n())
# age로 정렬
pf.fc_by_age <- arrange(pf.fc_by_age, age)
head(pf.fc_by_age)

## Conditional Means Alternative Code
pf.fc_by_age <- pf %.%
  group_by(age) %.%
  summarise(friend_count_mean = mean(friend_count),
            friend_count_median = median(friend_count),
            n = n()) %.%
  arrange(age)

head(pf.fc_by_age, 20)

## Overlaying Summaries with Raw Data
ggplot(aes(x = age, y = friend_count), data = pf) +
  xlim(13, 90) +
  geom_point(alpha = 0.05,
             position = position_jitter(h = 0),
             color = 'orange') +
  coord_trans(y = 'sqrt') +
  geom_line(stat = 'summary', fun.y = mean)

ggplot(aes(x = age, y = friend_count), data = pf) +
  xlim(13, 90) +
  geom_point(alpha = 0.05,
             position = position_jitter(h = 0),
             color = 'orange') +
  coord_trans(y = 'sqrt') +
  geom_line(stat = 'summary', fun.y = mean) +
  geom_line(stat = 'summary', fun.y = quantile, probs = .1,
            linetype = 2, color = 'blue') +
  geom_line(stat = 'summary', fun.y = quantile, probs = .5,
            color = 'blue') +
  geom_line(stat = 'summary', fun.y = quantile, probs = .9,
            linetype = 2, color = 'blue')

### Correlation
cor.test(pf$age, pf$friend_count, method = 'pearson')
# OR
with(pf, cor.test(age, friend_count, method = 'pearson'))

## Correlation on Subsets
with(subset(pf, age <= 70), cor.test(age, friend_count, 
                                     method = 'pearson'))

with(subset(pf, age <= 70), cor.test(age, friend_count, 
                                     method = 'spearman'))

## Strong Correlations
ggplot(aes(x = www_likes_received, y = likes_received), data = pf) +
  geom_point()

ggplot(aes(x = www_likes_received, y = likes_received), data = pf) +
  geom_point() +
  xlim(0, quantile(pf$www_likes_received, 0.95)) +
  ylim(0, quantile(pf$likes_received, 0.95)) +
  geom_smooth(method = 'lm', color = 'red')






