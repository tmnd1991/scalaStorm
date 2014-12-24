package storm.scala.dsl

import backtype.storm.tuple.Tuple

/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class NonEmittingTypedBolt[T<:Product](strings : String*) extends StormBolt(strings.toList){
  def typedExecute(t : T) : Unit
  override def execute(input: Tuple): Unit = {
    try{
      typedExecute(input.asScala.asInstanceOf[T])
      input.ack
    }
    catch{
      case t : Throwable => input.fail
    }
  }
}
