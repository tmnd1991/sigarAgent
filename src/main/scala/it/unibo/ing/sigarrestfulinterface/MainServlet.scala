package it.unibo.ing.sigarrestfulinterface

import java.net.URLClassLoader
import java.util.Date

import it.unibo.ing.smacs.monit.gatherer.DataGatherer
import it.unibo.ing.smacs.monit.gatherer.RecentBuffer
import it.unibo.ing.smacs.monit.gatherer.RecentBuffer
import it.unibo.ing.smacs.monit.model.MonitInfo
import org.scalatra._
import scalate.ScalateSupport
import spray.json._
import it.unibo.ing.sigar.restful.{RecentBuffer, SigarObject}
import it.unibo.ing.sigar.restful.model.SigarMeteredDataFormat._

class MainServlet extends SigarrestfulinterfaceStack {

  get("/:time") {
    val tick = params("time")
    val realDate = new Date(tick.toLong)
    if (RecentBuffer isLatest realDate){
      val newData = SigarObject.meteredData
      import DefaultJsonProtocol._
      RecentBuffer(realDate) = newData
      newData.toJson.compactPrint
    }
    else{
      import DefaultJsonProtocol._
      if (RecentBuffer contains realDate){
        RecentBuffer(realDate).toJson.compactPrint
      }
      else
        JsObject("error"->JsString("cannot retrieve monit data")).compactPrint
    }
  }
}
