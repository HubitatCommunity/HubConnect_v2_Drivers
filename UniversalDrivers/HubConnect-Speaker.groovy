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
	definition(name: "HubConnect Speaker", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/UniversalDrivers/HubConnect-Speaker.groovy")
	{
		capability "Refresh"
		capability "Actuator"

		capability "Initialize"
		capability "AudioVolume"
		capability "AudioNotification"
		capability "MusicPlayer"

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
	setVolume

	Does what it says.
*/
def setVolume(value)
{
	parent.sendDeviceEvent(device.deviceNetworkId, "setVolume", [value])
}


/*
	mute

	Mutes the device.
*/
def mute()
{
	// The server will update on/off status
	parent.sendDeviceEvent(device.deviceNetworkId, "mute")
}


/*
	unmute

	Unmutes the device.
*/
def unmute()
{
	// The server will update on/off status
	parent.sendDeviceEvent(device.deviceNetworkId, "unmute")
}


/*
	volumeUp

	volumeUp on the device.
*/
def volumeUp()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "volumeUp")
}


/*
	volumeDown

	volumeDown on the device.
*/
def volumeDown()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "volumeDown")
}


/*
	play

	play on the device.
*/
def play()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "play")
}


/*
	pause

	pause the device.
*/
def pause()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "pause")
}


/*
	nextTrack

	nextTrack on the device.
*/
def nextTrack()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "nextTrack")
}


/*
	playText

	Play [text] as TTS on the device.
*/
def playText(String text)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playText", [text])
}


/*
	previousTrack

	previousTrack on the device.
*/
def previousTrack()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "previousTrack")
}


/*
	restoreTrack

	Restore [trackuri] on the device.
*/
def restoreTrack(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "restoreTrack", [trackuri])
}


/*
	resumeTrack

	Resume [trackuri] on the device.
*/
def resumeTrack(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "resumeTrack", [trackuri])
}


/*
	setLevel

	Set volume to [trackuri] on the device.
*/
def setLevel(Integer level)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setLevel", [level])
}


/*
	setTrack

	Set track to [trackuri] on the device.
*/
def setTrack(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setTrack", [trackuri])
}


/*
	stop

	stop on the device.
*/
def stop()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "stop")
}


/*
	playText

	Play the [text] as TTS at volume [volumelevel] on the device.
*/
def playText(String text, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playText", [text, volumelevel])
}


/*
	playTextAndRestore

	Play the [text] as TTS on the device then restore defaults.
*/
def playTextAndRestore(String text)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTextAndRestore", [text])
}


/*
	playTextAndRestore

	Play the [text] as TTS at volume [volumelevel] on the device then restore defaults.
*/
def playTextAndRestore(String text, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTextAndRestore", [text, volumelevel])
}


/*
	playTextAndResume

	Play the [text] as TTS on the device then resume playback.
*/
def playTextAndResume(String text)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTextAndResume", [text])
}


/*
	playTextAndResume

	Play the [text] as TTS at volume [volumelevel] on the device then resume playback.
*/
def playTextAndResume(String text, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTextAndResume", [text, volumelevel])
}


/*
	playTrack

	Play the audio [trackuri] on the device.
*/
def playTrack(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrack", [trackuri])
}


/*
	playTrack

	Play the audio [trackuri] at volume [volumelevel] on the device.
*/
def playTrack(String trackuri, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrack", [trackuri, volumelevel])
}


/*
	playTrackAndRestore

	Play the audio [trackuri] on the device then restore defaults.
*/
def playTrackAndRestore(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrackAndRestore", [trackuri])
}


/*
	playTrackAndRestore

	Play the audio [trackuri] at volume [volumelevel] on the device then restore defaults.
*/
def playTrackAndRestore(String trackuri, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrackAndRestore", [trackuri, volumelevel])
}


/*
	playTrackAndResume

	Play the audio [trackuri] on the device then resume playback.
*/
def playTrackAndResume(String trackuri)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrackAndResume", [trackuri])
}


/*
	playTrackAndResume

	Play the audio [trackuri] at volume [volumelevel] on the device then resume playback.
*/
def playTrackAndResume(String trackuri, Integer volumelevel)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "playTrackAndResume", [trackuri, volumelevel])
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
	parent.syncDevice(device.deviceNetworkId, "switch")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
