package csvprocessors

import models.{Countries, CountryReader}

import scala.io.Source
import scala.util.Try
/**
  * This class is reading airport from source excel file
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
class CountryCSVReader (val fileName: String) extends CountryReader {
  override def readCountries(): Seq[Countries] = {
    for {
      line <- Source.fromFile(fileName, "ISO-8859-1").getLines().drop(1).toVector
      values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim.replace("\"", ""))
    } yield Countries(values(0), values(1), values(2), values(3), values(4), Try(values(5)).getOrElse(""))
  }
}
