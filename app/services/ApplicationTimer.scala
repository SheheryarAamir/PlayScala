package services

import java.time.{Clock, Instant}
import javax.inject._

import csvprocessors.{AirportCSVReader, CountryCSVReader, RunwayCSVReader}
import models.Data
import play.api.{Environment, Logger}
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
 * This class demonstrates how to run code when the
 * application starts and stops. It starts a timer when the
 * application starts. When the application stops it prints out how
 * long the application was running for.
 *
 * This class is registered for Guice dependency injection in the
 * [[Module]] class. We want the class to start when the application
 * starts, so it is registered as an "eager singleton". See the code
 * in the [[Module]] class to see how this happens.
 *
 * This class needs to run code when the server stops. It uses the
 * application's [[ApplicationLifecycle]] to register a stop hook.
  *
  *
  * *
  * Date                          Modified BY
  * 23 July, 2017                 Sheheryar Aamir
  */
 */
@Singleton
class ApplicationTimer @Inject() (clock: Clock, appLifecycle: ApplicationLifecycle, env: Environment) {

  // This code is called when the application starts.
  private val start: Instant = clock.instant
  Logger.info(s"ApplicationTimer demo: Starting application at $start.")
  //On Applicaiton Start
  applicationStartup()

  // When the application starts, register a stop hook with the
  // ApplicationLifecycle object. The code inside the stop hook will
  // be run when the application stops.
  appLifecycle.addStopHook { () =>
    val stop: Instant = clock.instant
    val runningTime: Long = stop.getEpochSecond - start.getEpochSecond
    Data.countries = Map()
    Data.airports = Map()
    Data.runways = Map()
    Logger.info(s"ApplicationTimer demo: Stopping application at ${clock.instant} after ${runningTime}s.")
    Future.successful(())
  }


  /**
    * On Application Startup.
    * Load three CSV files
    * And save them into application cache
    */
  def applicationStartup() = {
    Logger.info("1.2.3...Application Started")
    Data.countries = (new CountryCSVReader(env.getFile("public/countries.csv").getAbsolutePath).readCountries()).map(country => (country.id -> country)).toMap
    Data.airports = (new AirportCSVReader(env.getFile("public/airports.csv").getAbsolutePath).readAirports()).map(airports => (airports.id -> airports)).toMap
    Data.runways = (new RunwayCSVReader(env.getFile("public/runways.csv").getAbsolutePath).readRunways()).map(runways => (runways.id -> runways)).toMap
    //Logger.info("Application Started " + Data.airports(1).id)
  }

}
