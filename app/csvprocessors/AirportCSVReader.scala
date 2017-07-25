package csvprocessors

import javax.inject._

import models.{AirportReader, Airports}
import play.api.Environment

import scala.io.Source
import scala.util.Try
/**
  * This class is reading airport from source excel file
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */

class AirportCSVReader (val fileName: String)  extends AirportReader {

  override def readAirports(): Seq[Airports] = {
    for {
      line <- Source.fromFile(fileName, "ISO-8859-1").getLines().drop(1).toVector
      values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim.replace("\"", ""))
    } yield Airports(Try(values(0)).getOrElse(""), Try(values(1)).getOrElse(""),
      Try(values(2)).getOrElse(""), Try(values(3)).getOrElse(""), Try(values(4).toDouble).getOrElse(0.00),
      Try(values(5).toDouble).getOrElse(0.00), Try(values(6).toInt).getOrElse(0), Try(values(7)).getOrElse(""),
      Try(values(8)).getOrElse(""), Try(values(9)).getOrElse(""),
      Try(values(10)).getOrElse(""), Try(values(11)).getOrElse(""), Try(values(12)).getOrElse(""),Try(values(13)).getOrElse(""),
      Try(values(14)).getOrElse(""), Try(values(15)).getOrElse(""), Try(values(16)).getOrElse(""), Try(values(17)).getOrElse(""))
  }

}
