#################################
# Normalization
#################################

setwd("~/Documents/projects/course-data-analysis-basic/r-scripts")

### Calculate z-score normalization.
zscore <- function(x) {
  mu = mean(x, na.rm = TRUE)
  ro = sd(x, na.rm = TRUE)
  z = (x - mu) / ro
  return (z)
}

### Calculate min-max normalization.
minmax_normalize <- function(x, boundary_min, boundary_max) {
  normalized = (x - min(x)) / (max(x) - min(x)) * (boundary_max - boundary_min) + boundary_min
  return (normalized)
}


# Generate sample data
x = sample(-100:100, 50)

boundary_min = 0
boundary_max = 1

# Normalized data with min-max
normalized = minmax_normalize(x, boundary_min, boundary_max)
# Histogram of example data and normalized data
par(mfrow= c(1, 2))
hist(x, xlab="Data", col="lightblue", main="")
hist(normalized, xlab="Normalized Data", col="lightblue", main="")

# Normalized data with zscore
normalized = zscore(x)
# Histogram of example data and normalized data
par(mfrow= c(1, 2))
hist(x, xlab="Data", col="lightblue", main="")
hist(normalized, xlab="Normalized Data", col="lightblue", main="")



