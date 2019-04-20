package com.mirana.scala

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.scalatest.FunSuite

class Employee {
  var username: String = ""
  var age: Int = 0

  override def toString: String = s"username=${username}\tage=${age}"
}

case class Person @JsonCreator() (username: String, age: Int)

class ObjectMapperTest extends  FunSuite {



  test("objectMapper write as string") {


    //
    val objectMapper = new ObjectMapper() with  ScalaObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    //
    val yiifaa = Map("username" -> "yiifaa",
      "age" -> 32)
    //
    val output = objectMapper.writeValueAsString(yiifaa)
    println(output)
    //
    val employee = objectMapper.readValue[Employee](output)
    println(employee)

    val py = objectMapper.readValue[Person](output)
    println(py)
  }

}
