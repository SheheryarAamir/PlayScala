package csvprocessors

import models.{RunwayReader, Runways}

import scala.io.Source
import scala.util.Try
/**
  * This class is reading airport from source excel file
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
class RunwayCSVReader (val fileName: String) extends RunwayReader{
  override def readRunways(): Seq[Runways] = {
    for {
      line <- Source.fromFile(fileName, "ISO-8859-1").getLines().drop(1).toVector
      values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)").map(_.trim.replace("\"", ""))
    } yield Runways(Try(values(0)).getOrElse(""), Try(values(1)).getOrElse(""), Try(values(2)).getOrElse(""),
      Try(values(3).toInt).getOrElse(0), Try(values(4).toInt).getOrElse(0),
      Try(values(5)).getOrElse(""),
      Try(values(6).toInt).getOrElse(0), Try(values(7).toInt).getOrElse(0),
      Try(values(8)).getOrElse(""), Try(values(9).toDouble).getOrElse(0.00),
      Try(values(10).toDouble).getOrElse(0.00),
      Try(values(11).toInt).getOrElse(0), Try(values(12).toDouble).getOrElse(0.00), Try(values(13).toInt).getOrElse(0),
      Try(values(14)).getOrElse(""),
      Try(values(15).toDouble).getOrElse(0.00), Try(values(16).toDouble).getOrElse(0.00), Try(values(17).toInt).getOrElse(0),
      Try(values(18).toDouble).getOrElse(0.00), Try(values(19).toInt).getOrElse(0))
  }
}
