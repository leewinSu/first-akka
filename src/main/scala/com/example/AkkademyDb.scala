package com.example

import akka.actor.Actor
import akka.event.Logging
import com.example.AkkademyDbObject.SetRequest

import scala.collection.mutable

/**
  * @author suyao on 2019/5/8.
  * @version 1.0.0
  */

class AkkademyDb extends Actor{
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o);
  }
}

object AkkademyDbObject {
  case class SetRequest(
    key: String,
    value: Object
  )
}