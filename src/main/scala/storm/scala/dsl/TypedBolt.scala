package storm.scala.dsl

import backtype.storm.tuple.Tuple
/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class TypedBolt[I<:Product,O<:Product](strings : String*) extends StormBolt(strings.toList){
  def typedExecute(t : I) : Seq[O]
  override def execute(tuple: Tuple) : Unit = {
    val list = typedExecute(tuple.asScala.asInstanceOf[I])
    if (list.nonEmpty){
      for (e <- list)
        using anchor tuple emit(e.productIterator.toList:_*)
      tuple.ack
    }
    else{
      tuple.fail
    }
  }
}
