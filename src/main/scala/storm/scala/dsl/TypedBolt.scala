package storm.scala.dsl

import backtype.storm.tuple.Tuple
/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class TypedBolt[I<:Product,O<:Product](strings : String*) extends StormBolt(strings.toList){
  def typedExecute(t : I, st : Tuple) : Unit
  override def execute(tuple: Tuple) : Unit = {
    typedExecute(tuple.asScala.asInstanceOf[I], tuple)
  }
}
