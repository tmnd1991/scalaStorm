package storm.scala.dsl.additions


import org.slf4j.{LoggerFactory, Logger}
import storm.scala.dsl.SetupFunc

/**
 * Trait that adds logging capability to a SetupFunc implementer
 * @author Antonio Murgia
 * @version 06/12/2014
 */
trait Logging extends SetupFunc{
  protected var logger : Logger = _
  setup{
    logger = LoggerFactory.getLogger(this.getClass)
  }
}





