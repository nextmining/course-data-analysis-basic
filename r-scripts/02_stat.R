#################################
# Descriptive Statistics Basic
#################################

setwd("~/Documents/projects/course-data-analysis-basic/r-scripts")

### Install required packages
install.packages('ggplot2')
library(ggplot2)

trees
?trees

summary(trees)

sort(trees$Volume)

# Boxplot
boxplot(trees$Volume, data = trees, col = 'red')

qplot(x = Volume, y = Volume,
      data = trees,
      geom = 'boxplot')

# Mean
mean(trees$Volume)
# Variance
var(trees$Volume)
# Standard Deviation
sd(trees$Volume)

# Spearman Correlation Coefficient
cor(trees$Volume, trees$Height, method='spearman')
cor(trees$Volume, trees$Girth, method='spearman')

pairs(trees)
