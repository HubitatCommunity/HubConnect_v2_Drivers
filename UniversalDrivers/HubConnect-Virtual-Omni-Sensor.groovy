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
	definition(name: "HubConnect Virtual Omni Sensor", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/UniversalDrivers/HubConnect-Virtual-Omni-Sensor.groovy")
	{
		capability "Acceleration Sensor"
		capability "Carbon Dioxide Measurement"
		capability "Carbon Monoxide Detector"
		capability "Contact Sensor"
		capability "Energy Meter"
		capability "Illuminance Measurement"
		capability "Power Meter"
		capability "Presence Sensor"
		capability "Relative Humidity Measurement"
		capability "Smoke Detector"
		capability "Temperature Measurement"
		capability "Water Sensor"
		capability "Refresh"

		attribute "version", "string"

		command "arrived"
		command "departed"
		command "open"
		command "close"
		command "dry"
		command "wet"
		command "smokeDetected"
		command "smokeClear"
		command "CODetected"
		command "COClear"
		command "setCarbonDioxide",			[[name:"NUMBER", type: "NUMBER"]]
		command "setIlluminance",			[[name:"NUMBER", type: "NUMBER"]]
		command "motionActive"
		command "motionInactive"
		command "setRelativeHumidity",		[[name:"NUMBER", type: "NUMBER"]]
		command "setTemperature",			[[name:"NUMBER", type: "NUMBER"]]
		command "setEnergy",				[[name:"NUMBER", type: "NUMBER"]]
		command "setPower",					[[name:"NUMBER", type: "NUMBER"]]
		command "setVariable",				[[name:"STRING", type: "STRING"]]
		command "sync"
	}
}


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
	arrived

	Sets the presence device to present.
*/
def arrived()
{
	// The server will update presence status
	parent.sendDeviceEvent(device.deviceNetworkId, "arrived")
}


/*
	departed

	Sets the presence device to not present.
*/
def departed()
{
	// The server will update presence status
	parent.sendDeviceEvent(device.deviceNetworkId, "departed")
}


/*
	open

	Opens the contact device.
*/
def open()
{
	// The server will update open/close status
	parent.sendDeviceEvent(device.deviceNetworkId, "open")
}


/*
	close

	Closes the contact device.
*/
def close()
{
	// The server will update open/close status
	parent.sendDeviceEvent(device.deviceNetworkId, "close")
}


/*
	motionActive

	Sets the motion device to active.
*/
def motionActive()
{
	// The server will update active/inactive status
	parent.sendDeviceEvent(device.deviceNetworkId, "motionActive")
}


/*
	motionInactive

	Sets the motion device to inactive.
*/
def motionInactive()
{
	// The server will update active/inactive status
	parent.sendDeviceEvent(device.deviceNetworkId, "motionInactive")
}


/*
	dry

	Sets the water device to dry.
*/
def dry()
{
	// The server will update wet/dry status
	parent.sendDeviceEvent(device.deviceNetworkId, "dry")
}


/*
	wet

	Sets the water device to wet.
*/
def wet()
{
	// The server will update wet/dry status
	parent.sendDeviceEvent(device.deviceNetworkId, "wet")
}


/*
	smokeDetected

	Sets the smoke device to dry.
*/
def smokeDetected()
{
	// The server will update smoke status
	parent.sendDeviceEvent(device.deviceNetworkId, "smokeDetected")
}


/*
	smokeClear

	Sets the smoke device to clear.
*/
def smokeClear()
{
	// The server will update smoke status
	parent.sendDeviceEvent(device.deviceNetworkId, "smokeClear")
}


/*
	CODetected

	Sets the CO device to detected.
*/
def CODetected()
{
	// The server will update CO status
	parent.sendDeviceEvent(device.deviceNetworkId, "CODetected")
}


/*
	COClear

	Sets the CO device to clear.
*/
def COClear()
{
	// The server will update CO status
	parent.sendDeviceEvent(device.deviceNetworkId, "COClear")
}


/*
	setCarbonDioxide

	Set the Carbon Dioxide of the device to [carbonDioxide].
*/
def setCarbonDioxide(Float carbonDioxide)
{
	// The server will update carbon dioxide status
	parent.sendDeviceEvent(device.deviceNetworkId, "setCarbonDioxide", [carbonDioxide])
}


/*
	setRelativeHumidity

	Set the humidity of the device to [humidity] percent.
*/
def setRelativeHumidity(Float humidity)
{
	// The server will update humidity status
	parent.sendDeviceEvent(device.deviceNetworkId, "setRelativeHumidity", [humidity])
}


/*
	setIlluminance

	Set the illuminance of the device to [lux].
*/
def setIlluminance(Integer lux)
{
	// The server will update illuminance status
	parent.sendDeviceEvent(device.deviceNetworkId, "setIlluminance", [lux])
}


/*
	setTemperature

	Set the temperature of the device to [temperature] degrees.
*/
def setTemperature(Float temperature)
{
	// The server will update presence status
	parent.sendDeviceEvent(device.deviceNetworkId, "setTemperature", [temperature])
}


/*
	setPower

	Set the wattage of the power device to [power] watts.
*/
def setPower(Float power)
{
	// The server will update power status
	parent.sendDeviceEvent(device.deviceNetworkId, "setPower", [power])
}


/*
	setEnergy

	Set the energy device to [energy] kWh.
*/
def setEnergy(Float energy)
{
	// The server will update energy status
	parent.sendDeviceEvent(device.deviceNetworkId, "setEnergy", [energy])
}


/*
	setVariable

	Set the value of the variable device to string [variable].
*/
def setVariable(String variable)
{
	// The server will update variable status
	parent.sendDeviceEvent(device.deviceNetworkId, "setVariable", [variable])
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
	parent.syncDevice(device.deviceNetworkId, "moisture")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
