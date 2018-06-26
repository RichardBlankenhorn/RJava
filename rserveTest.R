
library(ISLR)

## eval(parse(text="Auto"))

myAdd <- function(x, y) {
  return(x+y)
}

myMult <- function(x, y) {
  return(x * y)
}

myList <- function(x, y, z) {
  l <- list(x,y,z)
  return(l)
}

myDF <- function() {
  mpgList <- array(Auto$mpg)
  return(mpgList)
}

myWholeDF <- function() {
  return(Auto)
}

myList <- function() {
  key <- "mpg"
  values <- Auto$mpg
  myList <- list()
  myList[[key]] <- values
  return(myList)
}

getDataFrame <- function(dsn) {
  return(eval(parse(text=dsn)))
}