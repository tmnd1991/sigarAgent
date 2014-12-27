package it.unibo.ing.sigar.restful.servlet

import it.unibo.ing.sigar.restful.SigarObject
import it.unibo.ing.sigar.restful.model.SigarMeteredDataFormat._
import spray.json._

class MyScalatraServlet extends SigarRestfulInterfaceStack {
  get("/") {
    SigarObject.meteredData.toJson.compactPrint
  }
}
