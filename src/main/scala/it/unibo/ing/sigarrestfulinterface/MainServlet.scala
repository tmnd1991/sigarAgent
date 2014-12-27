package it.unibo.ing.sigarrestfulinterface

import java.net.URLClassLoader

import org.scalatra._
import scalate.ScalateSupport
import spray.json._
import it.unibo.ing.sigar.restful.SigarObject
import it.unibo.ing.sigar.restful.model.SigarMeteredDataFormat._

class MainServlet extends SigarrestfulinterfaceStack {

  get("/") {
   SigarObject.meteredData.toJson.compactPrint
  }
}
