package models

import play.api.libs.json.Json

/**
  * Country Model
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
case class Country (cname:String, ccode:String)

object Country{

  implicit val countryFormat = Json.format[Country]

}
