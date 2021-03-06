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
def getDriverVersion() {[platform: "SmartThings", major: 2, minor: 0, build: 0]}

metadata
{
	definition(name: "HubConnect DomeAeon Plug", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/SmartThings/DeviceTypes/HubConnect-DomeAeon-Plug.groovy")
	{
		capability "Switch"
		capability "Outlet"
		capability "Power Meter"
		capability "Energy Meter"
		capability "Voltage Measurement"
		capability "Acceleration Sensor"
		capability "Contact Sensor"
		capability "Refresh"

		attribute "version", "string"

		command "sync"
	}

	tiles(scale: 2)
	{
		multiAttributeTile(name: "switch", type: "lighting", width: 6, height: 4, canChangeIcon: true)
		{
			tileAttribute("device.switch", key: "PRIMARY_CONTROL")
			{
				attributeState "on", label: 'On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "off", label: 'Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState: "turningOn"
				attributeState "turningOn", label: 'Turning On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "turningOff", label: 'Turning Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState: "turningOn"
			}
			tileAttribute("power", key: "SECONDARY_CONTROL")
			{
				attributeState "power", label: '${currentValue} W'
			}
		}
		standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2)
		{
			state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		}
		standardTile("sync", "sync", inactiveLabel: false, decoration: "flat", width: 2, height: 2)
		{
			state "default", label: 'Sync', action: "sync", icon: "st.Bath.bath19"
		}
		valueTile("version", "version", inactiveLabel: false, decoration: "flat", width: 2, height: 2)
		{
			state "default", label: '${currentValue}'
		}
		valueTile("acceleration", "device.acceleration", width: 2, height: 1, decoration: "flat")
		{
		    state "default", label: '${currentValue}'
		}
		valueTile("power", "device.power", width: 2, height: 1, decoration: "flat")
		{
		    state "default", label: '${currentValue} W'
		}
		valueTile("energy", "device.energy", width: 2, height: 1, decoration: "flat")
		{
		    state "default", label: '${currentValue} kWh'
		}
		valueTile("current", "device.current", width: 2, height: 1, decoration: "flat")
		{
		    state "default", label: '${currentValue} A'
		}
		valueTile("voltage", "device.voltage", width: 2, height: 1, decoration: "flat")
		{
		    state "default", label: '${currentValue} v'
		}

		main(["switch"])
		details(["switch", "sync", "power", "current", "voltage",
		         "acceleration", "refresh", "version"])
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
	on

	Turns the device on.
*/
def on()
{
	// The server will update on/off status
	parent.sendDeviceEvent(device.deviceNetworkId, "on")
}


/*
	off

	Turns the device off.
*/
def off()
{
	// The server will update on/off status
	parent.sendDeviceEvent(device.deviceNetworkId, "off")
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
	parent.syncDevice(device.deviceNetworkId, "power")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
