package storm.scala.dsl

/**
 * Created by tmnd on 17/10/14.
 */
class StormConfig(debug : Boolean = false) extends backtype.storm.Config{
  setDebug(debug)
}
