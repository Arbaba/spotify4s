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

sealed trait DeviceType
case object Computer extends DeviceType
case object Tablet extends DeviceType
case object Smartphone extends DeviceType
case object Speaker extends DeviceType
case object TV extends DeviceType
case object AVR extends DeviceType
case object STB extends DeviceType
case object AudioDongle extends DeviceType
case object GameConsole extends DeviceType
case object CastVideo extends DeviceType
case object CastAudio extends DeviceType
case object Automobile extends DeviceType
case object Unknown extends DeviceType

case class Device(
                  id: Option[String] = None,
                  is_active: Boolean,
                  is_private_session: Boolean,
                  is_restricted: Boolean,
                  name: String,
                  `type`: DeviceType,
                  volume_percent: Option[Integer]
                )
                 
object Device {
    private def getDeviceType(deviceName: String): DeviceType = deviceName.toLowerCase match {
      case "computer" => Computer
      case "tablet" => Tablet
      case "smartphone" => Smartphone
      case "speaker" => Speaker
      case "tv" => TV
      case "avr" => AVR
      case "stb" => STB
      case "audiodongle" => AudioDongle
      case "gameconsole" => GameConsole
      case "castvideo" => CastVideo
      case "castaudio" => CastAudio
      case "automobile" => Automobile
      case "unknown" => Unknown
    }

    private[spotify4s] def fromJson(json: DeviceJson): Device = Device(
        json.id,
        json.is_active,
        json.is_private_session,
        json.is_restricted,
        json.name,
        getDeviceType(json.`type`),
        json.volume_percent match {
            case None => None
            case Some(i) => Some(i.toInt)
        }
    )

}
