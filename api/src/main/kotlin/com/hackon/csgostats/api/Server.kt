package com.hackon.csgostats.api

import spark.Spark
import spark.Spark.get
import spark.Spark.post


/**
 * Created by haakon on 24.01.17.
 */
class Server{
  fun start() {
  }

}

fun main(args: Array<String>) {
  Spark.port(3000)
  post("/") {
    req,res->
    println(req)
    println(res)
  }
  get("/"){
    req, res ->
    println(req)
    println(res)
    res.body("This body... wow")
  }
}