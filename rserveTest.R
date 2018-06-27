library(reshape2)
library(ISLR)
library(pastecs)

## eval(parse(text="Auto"))

myList <- function(x, y, z) {
  l <- list(x,y,z)
  return(l)
}

myDF <- function() {
  mpgList <- array(Auto$mpg)
  return(mpgList)
}

myList <- function() {
  key <- "mpg"
  values <- Auto$mpg
  myList <- list()
  myList[[key]] <- values
  return(myList)
}

# Return A Chosen DataFrame in the ISLR Package
getDataFrame <- function(dsn) {
  return(eval(parse(text=dsn)))
}

# Return Summary Stats for Numeric Vals in DataFrame
getSummaryStatsNumeric <- function(dsn) {
  dta <- eval(parse(text=dsn))
  columnNames <- colnames(dta)
  colTypes <- sapply(dta, class)
  index <- 1
  myVec <- c()
  for (var in colTypes) {
    if (var == "numeric" | var == "integer" | var == "double") {
      myVec <- c(myVec, columnNames[index])
    }
    index <- index + 1
  }
  newDF <- dta[myVec[1]]
  index <- 1
  df <- 0
  for (nam in myVec) {
    if (index == 1) {
      df <- dta[nam]
      index <- index + 1
    }
    else {
      df <- cbind(df, dta[nam])
    }
  }
  stats <- round(stat.desc(df), digits = 2)
  return(list(stats, row.names(stats)))
}
