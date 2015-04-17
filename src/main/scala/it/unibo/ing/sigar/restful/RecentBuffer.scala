package it.unibo.ing.sigar.restful

import it.unibo.ing.sigar.restful.model.SigarMeteredData
import it.unibo.ing.utils.MostRecentKvalues

/**
 * Created by tmnd91 on 18/04/15.
 */
object RecentBuffer extends MostRecentKvalues[SigarMeteredData](150)
