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
Map getDriverVersion() {[platform: "Universal", major: 2, minor: 0, build: 0]}

metadata
{
	definition(name: "HubConnect Thermostat", namespace: "shackrat", author: "Steve White", importUrl: "https://raw.githubusercontent.com/HubitatCommunity/HubConnect/master/UniversalDrivers/HubConnect-Thermostat.groovy")
	{
		capability "Sensor"
		capability "Thermostat"
		capability "Temperature Measurement"
		capability "Thermostat Cooling Setpoint"
		capability "Thermostat Heating Setpoint"
		capability "Thermostat OperatingState"
		capability "Relative Humidity Measurement"
		capability "Thermostat Setpoint"

		attribute "version", "string"

		command "sync"
	}
}


/*
	installed

	Doesn't do much other than call initialize().
*/
void installed()
{
	initialize()
}


/*
	updated

	Doesn't do much other than call initialize().
*/
void updated()
{
	initialize()
}


/*
	initialize

	Doesn't do much other than call refresh().
*/
void initialize()
{
	unschedule()
	runEvery1Minute("refreshLRM")
}


/*
	uninstalled

	Reports to the remote that this device is being uninstalled.
*/
void uninstalled()
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
	auto

	Sets the thermostat operating mode to "auto".
*/
void auto()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "auto")
}


/*
	cool

	Sets the thermostat operating mode to "cool".
*/
void cool()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "cool")
}


/*
	emergencyHeat

	Turns on emergency heat.
*/
void emergencyHeat()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "emergencyHeat")
}


/*
	fanAuto

	Sets the fan operating mode to "auto".
*/
void fanAuto()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "fanAuto")
}


/*
	fanCirculate

	Sets the fan operating mode to "circulate".
*/
void fanCirculate()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "fanCirculate")
}


/*
	fanOn

	Sets the fan operating mode to "on".
*/
void fanOn()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "fanOn")
}


/*
	heat

	Sets the thermostat operating mode to "heat".
*/
void heat()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "heat")
}


/*
	off

	Sets the thermostat operating mode to "off".
*/
void off()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "off")
}


/*
	setCoolingSetpoint

	Sets the cooling setpoint to <temperature>.
*/
void setCoolingSetpoint(temperature)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setCoolingSetpoint", [temperature])
}


/*
	setHeatingSetpoint

	Sets the heating setpoint to <temperature>.
*/
void setHeatingSetpoint(temperature)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setHeatingSetpoint", [temperature])
}


/*
	setSchedule

	Sets the thermostat schedule to <schedule> (JSON).
*/
void setSchedule(schedule)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setSchedule", [schedule.toString()])
}


/*
	setThermostatFanMode

	Sets the fans operating mode to <fanmode>.
*/
void setThermostatFanMode(fanmode)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setThermostatFanMode", [fanmode])
}


/*
	setThermostatMode

	Sets the thermostat operating mode to <thermostatmode>.
*/
void setThermostatMode(thermostatmode)
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "setThermostatMode", [thermostatmode])
}


/*
	refreshLRM

	Refreshes lastRunningMode for Google Home compatibility.
*/
void refreshLRM()
{
	// Update lastRunningMode based on mode and operatingstate
	String lrm = getDataValue("lastRunningMode")
	String tm = device.currentValue("thermostatOperatingState") ?: "" + device.currentValue("thermostatMode") ?: ""

	if (tm.contains("cool") && lrm != "cool")
	{
		updateDataValue("lastRunningMode", "cool")
	}
	else
	{
		if (tm.contains("heat") && lrm != "heat")
		{
			updateDataValue("lastRunningMode", "heat")
		}
		else
		{
			if (tm.contains("auto") && lrm != "heat")
			{
				updateDataValue("lastRunningMode", "heat")
			}
		}
	}
}


/*
	sync

	Synchronizes the device details with the parent.
*/
void sync()
{
	// The server will respond with updated status and details
	parent.syncDevice(device.deviceNetworkId, "thermostat")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
