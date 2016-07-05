/**
 *  Change Nest Mode
 *
 *  Author: Matthew Kerfoot
 *  Date: 07/05/2016
 *
 *  Designed to set Nest to away (I was unable to find this functionality elsewhere).
 */

definition(
    name:        "Change Nest Mode",
    namespace:   "mkerfoot",
    author:      "mkkerfoot@gmail.com",
    description: "Adds the ability to change a Nest thermostat to 'away'.",
    category:    "Green Living",
    iconUrl:     "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url:   "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience%402x.png"
)

preferences {
    section("Change to this mode to...") {input "newMode", "mode", metadata:[values:["Away", "Home"]]}
    section("Change these thermostats modes...") {input "thermostats", "capability.thermostat", multiple: true }
}

def installed() {
  subscribe(location, changeMode)
  subscribe(app, changeMode)
}

def updated() {
  unsubscribe()
  subscribe(location, changeMode)
  subscribe(app, changeMode)
}

def changeMode(evt) {
  if(newMode == "Away") {log.info("Marking Away")
    thermostats?.away()
  }

  else {
    log.info("Marking Present")
    thermostats?.present()
  }
}