package controllers

import javax.inject._

import csvprocessors.{AirportCSVReader, CountryCSVReader, RunwayCSVReader}
import models.{Countries, Country, Data}
import play.api.{Environment, Logger}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

import scala.util.Try

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
  *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
 */
@Singleton
class HomeController @Inject()(env : Environment)extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index(s"Home Page"))
  }

  val cForm: Form[Country] = Form {
    mapping(
		"cname" -> text,
		"ccode" -> text
	  )(Country.apply)(Country.unapply)
  }

  /**
    * Query Function
    * @return
    * Sheheryar Aamir
    */
  def query = Action { implicit request =>
    val country = cForm.bindFromRequest().get
    var countries : Iterable[Countries] = Nil

    //Filter counties as per the criteria.
    if (country.cname.length() > 0){
      countries = Data.countries.values.filter(x => CountryFilter(x, country.cname))
    }else if (country.ccode.size > 0){
      countries = Data.countries.values.filter(x => x.code == country.ccode)
    }

    //Retrieve airports and runways for selected country
    if(countries != Nil && countries.size > 0){
      var airport = Data.airports.values.filter(x=> x.iso_country == countries.head.code).map(x=> x.id -> x.name).toMap
      var runway = Data.runways.values.map(x => x.airportRef -> x.id).toMap
      val result = airport.keySet.map {case (x) =>
        (airport.getOrElse((x), "--"), runway.getOrElse((x), "--") )
      }

      //Publish Result
      Ok(views.html.query(s"Result ${result}") )
    }else{

      Ok(views.html.index("Please select the correct criteria."))
    }
  }

  /**
    * Function to test partial/fuzzy
    * @param country
    * @param searchName
    * @return
    */
  def CountryFilter(country: Countries, searchName: String) = country.name.map(_.toLower).startsWith(searchName.map(_.toLower))

  /**
    * Report Function
    * @return
    * Sheheryar Aamir
    */
  def report = Action {

    //Transforming into map key value
    var runway = Data.runways.values.map(x => x.airportRef -> x.surface).toMap
    var airport = Data.airports.values.map(x => x.id -> x.iso_country).toMap


    //Retrieve runways based on airport keys
    val result = airport.keySet.map {case (x) =>
      (airport.getOrElse((x), "--"), runway.getOrElse((x), "--") )
    }.map(x=> (x._1,x._2)).groupBy(x => x._1).mapValues(_.size)


  //Retrieve top ten countries with highest airports
   var topTenAirports = Data.airports.values.groupBy(airport => airport.iso_country).mapValues(_.size).toSeq.sortBy(_._2)
      .reverse.take(10).map(x => ((Data.countries.values.filter(country => country.code == x._1)).toSeq(0).name, x._2, result(x._1)))

    //Retrieve ten countries with lowest airports
    var bottomTenAirports = Data.airports.values.groupBy(airport => airport.iso_country).mapValues(_.size).toSeq.sortBy(_._2).
      take(10).map(x => (Data.countries.values.filter(country => (country.code == x._1)).toSeq(0).name, x._2, result(x._1)))



   //Retrieve top 10 most common runway identifications
    var topCommonIdent = Data.runways.values.groupBy(runway => runway.leIdent).mapValues(_.size).toSeq.sortBy(_._2)
      .reverse.take(10).map(x => x._1 -> x._2)



    //Publish Result
    Ok(views.html.report(s"Report: 10 countries with highest number of airports: ${topTenAirports}        " +
      s"10 countries with lowest number of airports: ${bottomTenAirports}     " +
      s"Top 10 most common runway identifications${topCommonIdent}" ))
    //var l2 = Data.runways.filter()

  }

}
