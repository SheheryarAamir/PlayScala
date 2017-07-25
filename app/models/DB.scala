package models


import sorm._
/**
  * Created by Sheheryar Aamir on 20/07/2017.
  */

object DB extends Instance(entities = Seq(Entity[Country]()), url = "jdbc:h2:mem:test")
