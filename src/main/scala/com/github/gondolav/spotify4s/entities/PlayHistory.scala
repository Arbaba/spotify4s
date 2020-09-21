package com.github.gondolav.spotify4s.entities


private[spotify4s] case class PlayHistoryJson()

case class PlayHistory()

object PlayHistory{
 def fromJson(json: PlayHistoryJson): PlayHistory = {
  ???
 }
}