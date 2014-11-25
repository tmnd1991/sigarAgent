package it.unibo.ing.sigar.restful

import it.unibo.ing.sigar.restful.model.SigarMeteredData
import org.hyperic.sigar.{FileSystem, Sigar}

/**
 * Created by tmnd on 28/10/14.
 */
object SigarObject {
  private lazy val mySigar = new Sigar()
  private lazy val fileSystems = mySigar.getFileSystemList.filter(fs =>
    ((fs.getType != FileSystem.TYPE_UNKNOWN) && (fs.getType != FileSystem.TYPE_NONE))
  )
  def rxBytes = mySigar.getNetStat.getAllOutboundTotal
  def txBytes = mySigar.getNetStat.getAllInboundTotal
  def diskInfo = {
    var writeBytes = 0l
    var writes = 0l
    var readBytes = 0l
    var reads = 0l
    fileSystems.foreach(fs => {
      val fsUsage = mySigar.getFileSystemUsage(fs.getDevName)
      writeBytes += fsUsage.getDiskWriteBytes
      writes += fsUsage.getDiskWrites
      readBytes += fsUsage.getDiskReadBytes
      reads += fsUsage.getDiskReads
    })
    (reads, writes, readBytes, readBytes)
  }
  def processes = {
    mySigar.getProcList.size
    //mySigar.getThreadCpu.getTotal
  }
  def freeMemPercent = mySigar.getMem.getFreePercent
  def cpuPercent = mySigar.getCpuPerc.getCombined
  def meteredData = {
    lazy val r = diskInfo
    SigarMeteredData(cpuPercent, freeMemPercent, r._1, r._2, r._3, r._4, rxBytes,txBytes, processes)
  }
}
