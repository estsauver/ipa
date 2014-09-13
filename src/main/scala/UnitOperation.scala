import akka.actor.ActorRef

import scala.collection.immutable.Set
import scala.concurrent.Future

/**
 * Created by earljstsauver on 9/11/14.
 */

trait UnitOperation {
  type Precondition = (Set[Input]) => Boolean
  type Execution = (Set[Input]) => Future[Result]

  val requiredInputs:Set[Input]


  var inputs:Set[Input]
  val outputs:Set[Output]

  val preconditions:Seq[Precondition]

  val steps: Seq[Execution]

  def allInputsReceived:Boolean = {
    requiredInputs
      .forall((input) => inputs.contains(input))
  }

  def preconditionsMet:Boolean = {
    preconditions.forall((cond) => cond(inputs))
  }


  val results = ???

  def run(): Unit = {
    results = steps.map((step) => step(inputs))
  }

}


