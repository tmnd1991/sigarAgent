package it.unibo.ing.sigarrestfulinterface

import it.unibo.ing.sigar.restful.SigarObject
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by tmnd91 on 26/12/14.
 */
import spray.json._
import it.unibo.ing.sigar.restful.model.SigarMeteredDataFormat._

class SigarTest extends FlatSpec with Matchers{
  println (SigarObject.meteredData.toJson.toString())
}
