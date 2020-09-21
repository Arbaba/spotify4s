package com.github.gondolav.spotify4s.entities

private[spotify4s] case class PlaybackJson()

case class Playback()

object Playback {
 private[spotify4s] def fromJson(json: PlaybackJson): Playback = {
  ???
 }
}