import models.Data
import org.scalatest.FunSuite

/**
  * Created by Sheheryar Aamir on 23/07/2017.
  */
class CSVReaderTest extends FunSuite{
  test("Load CSV file Airport"){
    Data.airports.size shouldBe 46505
  }

  test("Load CSV file Countries"){
    Data.countries.size shouldBe 247
  }

  test("Load CSV file Runways"){
    Data.runways.size shouldBe 39536
  }
}
