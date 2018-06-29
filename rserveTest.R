library(reshape2)
library(ISLR)
library(pastecs)
library(ggplot2)

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

# Return Linear Model On Full Data Set (Numeric Vars)
linRegression <- function(dsn, respVar) {
  dta <- eval(parse(text=dsn))
  columnNames <- colnames(dta)
  colTypes <- sapply(dta, class)
  index <- 1
  myVec <- c()
  for (var in colTypes) {
    if (var == "numeric" | var == "integer" | var == "double") {
      if (columnNames[index] != respVar) {
        myVec <- c(myVec, columnNames[index])
      }
    }
    index <- index + 1
  }
  form <- as.formula(paste(respVar, "~", paste(myVec, collapse=" + ")))
  mod <- glm(form, data = dta)
  sum <- summary(mod)
  return(list(sum['coefficients'], row.names(as.data.frame(sum['coefficients'])), sum['aic'], sum['null.deviance'], sum['deviance']))
}




