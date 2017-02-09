package com.hackon.csgostats.api

import tornadofx.App
import tornadofx.View
import tornadofx.hbox
import tornadofx.label

/**
 * Created by haakon on 25.01.17.
 */

class HelloWorld: View(){
  override val root = hbox {
    label("HelloWorld")
  }
}

class HelloWorldApp: App(HelloWorld::class)