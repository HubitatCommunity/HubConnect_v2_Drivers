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
	definition(name: "HubConnect Lock", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/UniversalDrivers/HubConnect-Lock.groovy")
	{
		capability "Actuator"
		capability "Lock"
		capability "Lock Codes"
		capability "Battery"
		capability "Refresh"

		attribute "lastCodeName", "string"
		attribute "version", "string"

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
	lock

	Locks the lock.
*/
def lock()
{
	// The server will update lock status
	parent.sendDeviceEvent(device.deviceNetworkId, "lock")
}


/*
	unlock

	Unlocks the lock.
*/
def unlock()
{
	// The server will update lock status
	parent.sendDeviceEvent(device.deviceNetworkId, "unlock")
}



/*
	setCodeLength

	Sets the code length for the lock.
*/
def setCodeLength(length)
{
	// The server will respond with the a "codeLength" event
	parent.sendDeviceEvent(device.deviceNetworkId, "setCodeLength", [length])
}


/*
	deleteCode

	Deletes the code at slot <codeNumber> for this lock.
*/
def deleteCode(codeNumber)
{
	// The server will respond with the a "codeChanged" event
	parent.sendDeviceEvent(device.deviceNetworkId, "deleteCode", [codeNumber])
}


/*
	setCode

	Adds a code at slot <codeNumber> with <code> and <name> for this lock.
*/
def setCode(codeNumber, code, name = null)
{
	// The server will respond with the a "codeChanged" event
	parent.sendDeviceEvent(device.deviceNetworkId, "setCode", [codeNumber, code, name])
}


/*
	getCodes

	Fetches all codes for this lock.
*/
def getCodes()
{
	// The server will respond with the a "codeChanged" event
	parent.sendDeviceEvent(device.deviceNetworkId, "getCodes")
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
	parent.syncDevice(device.deviceNetworkId, "lock")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
