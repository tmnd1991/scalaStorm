package storm.scala.dsl

import backtype.storm.tuple.Tuple

/**
 * Created by tmnd91 on 22/12/14.
 */
abstract class TypedSpout[T<:Product](distributed: Boolean, strings : String*) extends StormSpout(strings.toList, distributed)
