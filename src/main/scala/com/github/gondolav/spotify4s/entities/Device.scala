package com.github.gondolav.spotify4s.entities
import ujson.{Null, Value}

import upickle.default._

private[spotify4s] case class DeviceJson(
                                         id: Option[String] = None,
                                         is_active: Boolean,
                                         is_private_session: Boolean,
                                         is_restricted: Boolean,
                                         name: String,
                                         `type`: String,
                                         volume_percent: Option[Long]= None
                                        )

private[spotify4s] object DeviceJson {
  implicit val rw: ReadWriter[DeviceJson] = macroRW

  implicit def OptionReader[T: Reader]: Reader[Option[T]] = reader[Value].map[Option[T]] {
    case Null => None
    case jsValue => Some(read[T](jsValue))
  }
}

case class Device(
                  id: Option[String] = None,
                  is_active: Boolean,
                  is_private_session: Boolean,
                  is_restricted: Boolean,
                  name: String,
                  `type`: String,
                  volume_percent: Option[Integer]
                )
                 
object Device {
    private[spotify4s] def fromJson(json: DeviceJson): Device = Device(
        json.id,
        json.is_active,
        json.is_private_session,
        json.is_restricted,
        json.name,
        json.`type`,
        json.volume_percent match {
            case None => None
            case Some(i) => Some(i.toInt)
        }
    )

}
