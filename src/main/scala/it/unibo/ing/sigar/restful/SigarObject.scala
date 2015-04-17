package it.unibo.ing.sigar.restful

import it.unibo.ing.sigar.restful.model.SigarMeteredData
import org.hyperic.sigar.{FileSystem, Sigar}

/**
 * @author  Antonio Murgia
 * @version 28/10/14.
 */
object SigarObject {
  private val mySigar = new Sigar()
  private val fileSystems = mySigar.getFileSystemList.filter(fs =>
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
  def uptime = mySigar.getUptime.getUptime
  def coreNumber = mySigar.getCpuList.length
  def osName = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch")
  val cpuInfo = mySigar.getCpuInfoList.iterator.next()
  def cpuName = cpuInfo.getVendor + " " + cpuInfo.getModel + " @ " + cpuInfo.getMhz + "Mhz"
  def meteredData = {
    val r = diskInfo
    SigarMeteredData(cpuPercent, freeMemPercent, r._1, r._2, r._3, r._4,rxBytes, txBytes, processes, uptime, coreNumber, osName, cpuName)

  }
}
