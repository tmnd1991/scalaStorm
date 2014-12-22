package storm.scala.dsl

/**
 * @author  Antonio Murgia
 * @version 22/12/2014
 */
import java.util.Date
import backtype.storm.topology.{BoltDeclarer, TopologyBuilder}

/**
 * Extension over Storm's TopologyBuilder,
 * adds compile-time typesafety checks for TypedSpouts and Bolts
 * I know it adds a little bit of boilerplate code, and it's not so scala idiomatic...
 * It's not immutable, I hope to fix that soon.
 * It's just a proof of concept of what I can do with scala typesystem,
 */
class TypedTopologyBuilder extends TopologyBuilder{

  private val _spouts = scala.collection.mutable.Map[String, TypedSpout[_]]()

  /**
   *
   * @param spoutName the name of the spout that will be the input to the bolt
   * @param spout     the spout that will be the input to the bolt, it should be already registered!
   * @param boltName  the name of the bolt (unique)
   * @param bolt      the bolt that will receive data from the spout
   * @param parallelismHint the parallelism of the bolt
   * @tparam T        the Type of the exchanged data for compiletime typesafety checks
   * @return          a BoltDeclarer as it would have returned a TopologyBuilder
   */
  def setBolt[T <: Product](spoutName: String, spout: TypedSpout[T],
                            boltName: String, bolt: TypedBolt[T, _],
                            parallelismHint: Number = null): BoltDeclarer = {
    if (!_spouts.contains(spoutName)){
      setSpout(spoutName, spout)
      _spouts(spoutName) = spout
    }
    else{
      /*
       It's not a bug, it's a feature!
       This check may seem not necessary, but in fact it is.
       The problem is that someone could try to fool the typesafety of this method
       registering a not typed spout with a name and then trying to set a TypedBolt
       to its output, that isn't a big deal, maybe it would work, maybe not.
       Working this way it's not adding any typesafety to the system, so... why bother?
       If you don't need/want typesafety just use the Original TopologyBuilder
       (backtype.storm.topology.TopologyBuilder).
       */
      if (_spouts(spoutName) != spout)
        throw new RuntimeException("Passed SpoutName is already registered but Spouts are not equals")
    }
    setBolt(boltName, bolt, parallelismHint)
  }

  /**
   *
   * @param emitterName   the name of the emitting bolt (unique)
   * @param emitter       the bolt that will emit data to the receiver bolt, IT MUST BE ALREADY REGISTERED
   * @param receiverName  the name of the receiving bolt (unique)
   * @param receiver      the bolt that will receive date from the emmitting bolt
   * @param parallelismHint the parallelism of the bolt
   * @tparam T            the Type of the exchanged data for compiletime typesafety checks
   * @return              a BoltDeclarer as it would have returned a TopologyBuilder
   */
  def setBolt[T <: Product](emitterName : String, emitter : TypedBolt[_,T],
                            receiverName : String, receiver : TypedBolt[T,_],
                            parallelismHint: Number = null) : BoltDeclarer = {
    setBolt(receiverName, receiver, parallelismHint)
  }
}


class provaSpout extends TypedSpout[(Date,String)](false,"data","stringa") {
  override def nextTypedTuple: List[(Date, String)] = {
    List((new Date(),"ciccia"))
  }
}
class provaBolt extends TypedBolt[(Date,String),Tuple1[String]]("stringa") {
  override def typedExecute(t: (Date, String)): Seq[Tuple1[String]] = {
    List(Tuple1("" + t._1 + t._2))
  }
}
