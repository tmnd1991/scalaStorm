package storm.scala.dsl

/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class TypedSpout[T<:Product](distributed: Boolean, strings : String*) extends StormSpout(strings.toList, distributed){
  def nextTypedTuple : List[T]
  override def nextTuple() : Unit = {
    for (t <- nextTypedTuple){
      emit(t.productIterator.toList:_*)
    }
  }
}
