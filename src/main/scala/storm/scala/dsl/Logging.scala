package storm.scala.dsl

import org.slf4j.{LoggerFactory, Logger}

/**
 * Created by tmnd on 10/11/14.
 */
trait Logging extends SetupFunc{
  protected var logger : Logger = _
  setup{
    logger = LoggerFactory.getLogger(this.getClass)
  }
}
