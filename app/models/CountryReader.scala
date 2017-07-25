package models

/**
  * Country Model
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
trait CountryReader {

  /**
    * @return A [[Seq]] containing all the counties.
    */
  def readCountries(): Seq[Countries]

}
case class Countries(id: String, code: String, name: String, continent: String, wikipediaLink: String,
                     keywords: String)
