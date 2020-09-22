/*
 *	Copyright 2019-2020 Steve White, Retail Media Concepts LLC.
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *	use this file except in compliance with the License. You may obtain a copy
 *	of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *	License for the specific language governing permissions and limitations
 *	under the License.
 *
 *
 */
def getDriverVersion() {[platform: "Universal", major: 2, minor: 0, build: 0]}

metadata
{
	definition(name: "HubConnect RM Global Variable Connector", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/Hubitat/drivers/HubConnect-RM-Global-Variable-Connector.groovy")
	{
		capability "Sensor"
		capability "AccelerationSensor"
		capability "CarbonDioxideMeasurement"
		capability "CarbonMonoxideDetector"
		capability "ContactSensor"
		capability "IlluminanceMeasurement"
		capability "MotionSensor"
		capability "PresenceSensor"
		capability "RelativeHumidityMeasurement"
		capability "SmokeDetector"
		capability "TemperatureMeasurement"
		capability "WaterSensor"
		capability "Refresh"

		attribute "version", "string"
		attribute "variable", "string"

		command "accelerationActive"
		command "accelerationInactive"
		command "arrived"
		command "close"
		command "COClear"
		command "CODetected"
		command "departed"
		command "dry"
		command "motionActive"
		command "motionInactive"
		command "open"
		command "setCarbonDioxide",    [[type: "NUMBER"]]
		command "setIlluminance",      [[type: "NUMBER"]]
		command "setRelativeHumidity", [[type: "NUMBER"]]
		command "setTemperature",      [[type: "NUMBER"]]
		command "setVariable",         [[type: "string"]]
		command "smokeClear"
		command "smokeDetected"
		command "wet"
		command "sync"
	}
}


/*
	sendEvent for each Device Info page's button

*/
def accelerationActive()      { parent.sendDeviceEvent(device.deviceNetworkId, "acceleration", "active") }
def accelerationInactive()    { parent.sendDeviceEvent(device.deviceNetworkId, "acceleration", "inactive") }
def arrived()                 { parent.sendDeviceEvent(device.deviceNetworkId, "presence", "arrived") }
def close()                   { parent.sendDeviceEvent(device.deviceNetworkId, "close", "close") }
def COClear()                 { parent.sendDeviceEvent(device.deviceNetworkId, "carbonDioxide", "clear") }
def CODetected()              { parent.sendDeviceEvent(device.deviceNetworkId, "carbonDioxide", "detected") }
def departed()                { parent.sendDeviceEvent(device.deviceNetworkId, "departed", "departed") }
def dry()                     { parent.sendDeviceEvent(device.deviceNetworkId, "water", "dry") }
def motionActive()            { parent.sendDeviceEvent(device.deviceNetworkId, "motion","active") }
def motionInactive()          { parent.sendDeviceEvent(device.deviceNetworkId, "motion", "inactive") }
def open()                    { parent.sendDeviceEvent(device.deviceNetworkId, "open", "open") }
def setCarbonDioxide(val)     { parent.sendDeviceEvent(device.deviceNetworkId, "setCarbonDioxide", [val]) }
def setIlluminance(val)       { parent.sendDeviceEvent(device.deviceNetworkId, "setIlluminance", [val]) }
def setRelativeHumidity(val)  { parent.sendDeviceEvent(device.deviceNetworkId, "setRelativeHumidity", [val]) }
def setTemperature(val)       { parent.sendDeviceEvent(device.deviceNetworkId, "setTemperature", [val]) }
def setVariable(val)          { parent.sendDeviceEvent(device.deviceNetworkId, "setVariable", [val]) }
def smokeClear()              { parent.sendDeviceEvent(device.deviceNetworkId, "smoke", "clear") }
def smokeDetected()           { parent.sendDeviceEvent(device.deviceNetworkId, "smoke", "detected") }
def wet()                     { parent.sendDeviceEvent(device.deviceNetworkId, "water", "wet") }


/*
	installed

	Doesn't do much other than call initialize().
*/
def installed()
{
	initialize()
}


/*
	updated

	Doesn't do much other than call initialize().
*/
def updated()
{
	initialize()
}


/*
	initialize

	Doesn't do much other than call refresh().
*/
def initialize()
{
	refresh()
}


/*
	uninstalled

	Reports to the remote that this device is being uninstalled.
*/
def uninstalled()
{
	// Report
	parent?.sendDeviceEvent(device.deviceNetworkId, "uninstalled")
}


/*
	parse

	In a virtual world this should never be called.
*/
def parse(String description)
{
	log.trace "Msg: Description is $description"
}



/*
	refresh

	Refreshes the device by requesting an update from the client hub.
*/
def refresh()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "refresh")
}


/*
	sync

	Synchronizes the device details with the parent.
*/
def sync()
{
	// The server will respond with updated status and details
	parent.syncDevice(device.deviceNetworkId, "ContactSensor")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
