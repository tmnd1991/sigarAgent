package it.unibo.ing.sigarrestfulinterface

import java.net.URLClassLoader
import java.util.Date

import it.unibo.ing.sigar.restful.model.SigarMeteredData
import org.scalatra._
import scalate.ScalateSupport
import spray.json._
import it.unibo.ing.sigar.restful.{RecentBuffer, SigarObject}
import it.unibo.ing.sigar.restful.model.SigarMeteredDataFormat._

class MainServlet extends SigarrestfulinterfaceStack {

  get("/avaiable") {
    Ok(RecentBuffer.keys.map(_.getTime).mkString("[",",\n","]"))
  }

  get("/samples/:from/:to") {
    val from = params("from")
    val to = params("to")
    val startDate = new Date(from.toLong)
    val endDate = new Date(to.toLong)
    val results = RecentBuffer.between(startDate,endDate)
    Ok(results.toJson.compactPrint)
  }
}
