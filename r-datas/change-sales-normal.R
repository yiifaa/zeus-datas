sales <- read.table("changde-sales.txt", sep=",", header = TRUE)
sales.matrix <- data.matrix(sales)
sales.mean <- apply(sales.matrix, 2, mean)
sales.mid <- apply(sales.matrix, 2, median)
sales.sum <- apply(sales.matrix, 2, sum)

#
over_avg <- function(x) {
  avg <- mean(x)
  x < avg
}
#
sales.over <- apply(sales.matrix, 2, over_avg)

sales.result <- list(mean=sales.mean, 
                     mid=sales.mid, 
                     sum=sales.sum,
                     over=sales.over)



