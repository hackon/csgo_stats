package com.hackon.csgostats.api

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import spark.Spark.*
import java.time.Instant


/**
 * Created by haakon on 24.01.17.
 */
class Server{
  fun start() {
  }

}

fun main(args: Array<String>) {
  val gson = GsonBuilder()
      .registerTypeAdapter(Instant::class.java, object :TypeAdapter<Instant>(){
        override fun write(out: JsonWriter, value: Instant?) {out.value(value?.epochSecond)}
        override fun read(`in`: JsonReader) = Instant.ofEpochSecond(`in`.nextLong())
      })
      .serializeNulls()
      .create()
  port(3000)
  ipAddress("127.0.0.1")
  post("/") {
    req,res->
    println("${Instant.now()}::${req.body()}")
//    println("${Instant.now()}::${gson.fromJson(req.body(), Data::class.java)}")
  }
}

data class Data(
    val provider:Provider?=null,
    val map:CSMap?=null,
    val round:Round?=null,
    val player:CSPlayer?=null
)

data class Round (
    val phase: String = "",
    val win_team:String= "",
    val bomb:String = ""
)

data class CSPlayer(
    val steamid: String,
    val name: String,
    val team:String,
    val activity:String,
    val state:PlayerState,
    val weapons:PlayerWeapons,
    val match_stats:MatchStats
)

data class MatchStats (
    val kills:Int,
    val assists:Int,
    val deaths:Int,
    val mvps:Int,
    val score:Int
)

data class PlayerWeapons (
    val weapon_0:PlayerWeapon?=null,
    val weapon_1:PlayerWeapon?=null,
    val weapon_2:PlayerWeapon?=null,
    val weapon_3:PlayerWeapon?=null,
    val weapon_4:PlayerWeapon?=null
)

data class PlayerWeapon(
    val name: String,
    val paintkit:String="",
    val type:String,
    val state:String,
    val ammo_clip:Int?=null,
    val ammo_clip_max:Int?=null,
    val ammo_reserve:Int?=null
)

data class PlayerState (
    val health:Int,
    val armor:Int,
    val helmet:Boolean = false,
    val flashed:Int,
    val smoked:Int,
    val burning:Int,
    val money:Int,
    val round_kills:Int,
    val round_killhs:Int
)

data class CSMap(
//    val mode:String,
    val name:String,
    val phase:String,
    val round:Int,
    val team_ct:Score,
    val team_t:Score
)

data class Score (val score:Int)

data class Provider (
    val name:String,
    val appid:Int,
    val version:Int,
    val steamid:String,
    val timestamp:Instant
)