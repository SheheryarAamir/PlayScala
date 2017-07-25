package models
import javax.inject._
/**
  * Cache Data
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
object Data {
  var airports: Map[String, Airports] = Map()
  var countries: Map[String, Countries] = Map()
  var runways: Map[String, Runways] = Map()
}
