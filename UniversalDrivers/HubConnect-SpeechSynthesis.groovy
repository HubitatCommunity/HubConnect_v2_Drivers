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
	definition(name: "HubConnect SpeechSynthesis", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/UniversalDrivers/HubConnect-SpeechSynthesis.groovy")
	{
		capability "Actuator"
		capability "AudioVolume"
		capability "SpeechSynthesis"
		capability "Initialize"
		capability "Refresh"

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

	Initialize the speaker.
*/
def initialize()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "initialize")
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
	mute

	Turns the speaker on.
*/
def mute()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "mute")
}


/*
	unmute

	Turns the speaker off.
*/
def unmute()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "unmute")
}


/*
	setVolume

	Adjust the volume.
*/
def setVolume(value)
{
	// The server will update volume status
	parent.sendDeviceEvent(device.deviceNetworkId, "setVolume", [value])
}


/*
	speak

	sends TTS words.
*/
def speak(value)
{
	// The server will update strobe status
	parent.sendDeviceEvent(device.deviceNetworkId, "speak", [value])
}


/*
	volumeUp

	Turns up the speaker volume.
*/
def volumeUp()
{
	// The server will update speaker status
	parent.sendDeviceEvent(device.deviceNetworkId, "volumeUp")
}


/*
	volumeDown

	Turns down the speaker volume.
*/
def volumeDown()
{
	// The server will update speaker status
	parent.sendDeviceEvent(device.deviceNetworkId, "volumeDown")
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
	parent.syncDevice(device.deviceNetworkId, "SpeechSynthesis")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
