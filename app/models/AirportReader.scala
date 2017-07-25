package models

/**
  * Airport Model
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */

trait AirportReader {

  /**
    * @return A [[Seq]] containing all the airports.
    */
  def readAirports(): Seq[Airports]

}
case class Airports (id: String, ident: String, airportType: String, name: String, latitudeDeg: Double, longitudeDeg :Double,
  elevationFt: Int, continent: String, iso_country:String, iso_region:String, municipality: String, scheduledService:String,
                     gpsCode: String, iataCode: String, localCode: String, homeLink: String, wikipediaLink: String, keywords:String)
