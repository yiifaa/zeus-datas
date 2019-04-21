package com.mirana.scala

import java.io.{File, PrintWriter}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object DataTransformer {

  def main(args: Array[String]): Unit = {
    val buffSource = Source.fromFile("changde.txt")
    val timePat = "^\\d{6}$".r
    var timeArr: ArrayBuffer[String] = ArrayBuffer("AREA")
    var results : mutable.ListMap[String, ArrayBuffer[String]] = new mutable.ListMap()
    for(line <- buffSource.getLines()) {
      if (timePat.pattern.matcher(line).matches()) {
        timeArr += line
      } else {
        val values = line.split("\t").zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
        val pair = values match {
          case Array(area, num) => (area, num)
        }
        val (area, num) = pair
        var result = results.get(area)
        if(result.isEmpty) {
          result = Some(new ArrayBuffer[String]())
        }
        val item = result.get
        item += num
        results.update(area, item)
      }
    }
    buffSource.close()
    val head = timeArr.mkString(",")
    val output = new File("changde-sales.txt")
    if(!output.exists()) {
      output.createNewFile()
    }
    val writer = new PrintWriter(output)
    writer.write(head + "\n")
    results.map(item => item._1 + "," + item._2.mkString(","))
           .foreach(item => writer.write(item + "\n"))

    writer.close()
  }

}
