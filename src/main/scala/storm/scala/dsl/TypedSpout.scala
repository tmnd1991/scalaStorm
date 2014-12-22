package storm.scala.dsl

import scala.reflect.runtime.universe._
/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class TypedSpout[T<:Product](distributed: Boolean, strings : String*) extends StormSpout(strings.toList){
  def nextTypedTuple : List[T]
  override def nextTuple() : Unit = {
    for (t <- nextTypedTuple){
      emit(t.productIterator.toList)
    }
  }
}