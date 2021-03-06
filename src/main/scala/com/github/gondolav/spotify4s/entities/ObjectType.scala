package com.github.gondolav.spotify4s.entities

sealed trait ObjectType

object ObjectType {
  def fromString(s: String): ObjectType = s.toLowerCase match {
    case "album" => AlbumObj
    case "artist" => ArtistObj
    case "episode" => EpisodeObj
    case "show" => ShowObj
    case "track" => TrackObj
  }
}

case object AlbumObj extends ObjectType {
  override def toString: String = "album"
}

case object ArtistObj extends ObjectType {
  override def toString: String = "artist"
}

case object EpisodeObj extends ObjectType {
  override def toString: String = "episode"
}

case object ShowObj extends ObjectType {
  override def toString: String = "show"
}

case object TrackObj extends ObjectType {
  override def toString: String = "track"
}
