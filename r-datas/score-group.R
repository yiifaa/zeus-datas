score <- read.table("score.txt", sep="\t", header = TRUE)
score.top10 <- head(score, 20)
score.part <- score.top10[, c(3, 6, 8, 9)]
#agg.sum <- aggregate(score.part, by = list("专业码"), fun = sum)
score.new <- data.frame(Z=score.part$专业码, C=score.part$初试成绩, ALL=score.part$复试总成绩)
agg.sumary <- with(score.new, {
  agg.sum <- aggregate(score.new,  by = list(Group.sum=Z), FUN = sum)
  agg.mean <- aggregate(score.new,  by = list(Group.mean =Z), FUN = mean)
  cbind(agg.sum[c(1, 3, 4)], agg.mean[c(3, 4)])
})
names(agg.sumary) <- c("ZY", "C.S", "A.S", "C.M", "A.M")
