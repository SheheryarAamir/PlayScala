package models

/**
  * Runway Model
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
trait RunwayReader {
  /**
    * @return A [[Seq]] containing all the runways.
    */
  def readRunways(): Seq[Runways]
}

case class Runways(id: String, airportRef:String, airportIdent:String, lengthFt:Int, widthFt:Int, surface:String,
                   lighted:Int, closed:Int, leIdent:String, leLatitudeDeg: Double, leLongitudeDeg: Double, leElevationFt:Int,
                   leHeadingDegT:Double, leDisplacedThresholdFt:Int,	heIdent:String,	heLatitudeDeg:Double,
                   heLongitudeDeg:Double, heElevationFt:Int, heHeadingDegT:Double, heDisplacedThresholdFt:Int)

