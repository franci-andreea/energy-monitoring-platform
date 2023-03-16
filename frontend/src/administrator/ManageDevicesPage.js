import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import './AdminPage.css'

const ManageDevicesPage = () =>
{
    let navigate = useNavigate();

    const [currentUser, _] = useState(useLocation().state);

    const [refreshKey, setRefreshKey] = useState(0);
    const [devices, setDevices] = useState([]);
    const [unfilteredDevices, setUnfilteredDevices] = useState([]);
    const [users, setUsers] = useState([]);
    const [selectedUsername, setSelectedUsername] = useState("");
    const [selectedUsernameForDevice, setSelectedUsernameForDevice] = useState("");

    const [description, setDescription] = useState("");
    const [address, setAddress] = useState("");
    const [maxHourlyEnergyConsumption, setMaxHourlyEnergyConsumption] = useState("");

    var filteredDevices = [];

    useEffect(
        () => {
            if(checkUser())
            {
                getUsers().then((val) => {setUsers(val)});
                getDevices().then((val) => {setDevices(val)});
                getDevices().then((val) => {setUnfilteredDevices(val)});
            }
        }, [refreshKey]
    )

    useEffect(
        () => {
            filterDevivcesByUser().then((val) => {setDevices(val)});
        }, [selectedUsername]
    )

    async function checkUser()
    {
        if(currentUser === null || currentUser.role !== 'ADMIN')
        {
            navigate('/');
            return false;
        }

        return true;
    }

    async function getUsers()
    {
        return await axios.get(process.env.REACT_APP_SERVER_URL + '/get-users', {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(
            response => {
                return response.data;
            }
        )
        .catch(
            function(error)
            {
                console.log("get users from backend - some error occurred");
                console.log(error);
                return [];
            }
        )
    }

    async function getDevices()
    {
        return await axios.get(process.env.REACT_APP_SERVER_URL + '/get-devices', {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(
            response => {
                return response.data;
            }
        )
        .catch(
            function(error)
            {
                console.log("get devices from backend - some error occurred");
                console.log(error);
                return [];
            }
        )
    }

    function addDevice()
    {
        let addDeviceDTO = {
            description: description,
            address: address,
            maxHourlyEnergyConsumption: maxHourlyEnergyConsumption,
            username:selectedUsernameForDevice
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/add-device', addDeviceDTO)
        .then(
            response => {
                if(response.status === 200)
                {
                    alert('Successfully added your device!');
                    setRefreshKey(refreshKey + 1);
                }
            }
        )
        .catch(
            function(error)
            {
                console.log(error);
            }
        )
    }

    function deleteDevice(device)
    {
        axios.post(process.env.REACT_APP_SERVER_URL + '/delete-device', device)
        .then(
            response => {
                if(response.status === 200)
                {
                    alert('Successfully deleted device #' + device.id);
                    setRefreshKey(refreshKey + 1);
                }
            }
        )
        .catch(
            function(error)
            {
                console.log(error);
            }
        )
    }

    async function filterDevivcesByUser()
    {
        filteredDevices = [];

        unfilteredDevices.forEach(
            function(device)
            {
                if(device.user.username === selectedUsername)
                    filteredDevices.push(device);
            }
        )

        return filteredDevices;
    }

    return (currentUser) ? (
        <div className="font-poppins bg-slate-50 min-h-screen">
            <div className="flex p-7">
                <div className="flex-none">
                    <button onClick={() => {navigate('/admin', {state: currentUser});}}>Admin section - {currentUser.username}</button>
                </div>

                <div className="flex-grow">

                </div>

                <button className="logout-button" onClick={() => {navigate('/');}}>↦</button>

            </div>

            <div className="flex flex-row m-9 space-x-24">
                <div className="w-14 h-14">
                    <button className="hover-button1" onClick={() => {navigate('/admin/manage-users', {state: currentUser});}}>USERS</button>
                </div>

                <div className="w-14 h-14">
                    <button className="selected-section" onClick={() => {navigate('/admin/manage-devices', {state: currentUser});}}>DEVICES</button>
                </div>
            </div>

            <div className="grid grid-cols-2 divide-x ml-5">
                <div>
                    <label>Filter devices by user: </label>
                    <select className="form-select font-poppins select-box mb-6" id="users" onChange={(e) => { setSelectedUsername(e.target.value) }}>
                        {users.map((user) => <option value={user.username} key={user.id}>{user.username}</option>)}
                    </select>

                    <button className="ml-5 button-user" onClick={() => {setRefreshKey(refreshKey + 1)}}>Clear filter</button>

                    <div className="space-y-3">
                        {devices.map(
                            (device) => 
                                <div className="flex flex-row ml-5 border-gray-200 border-2 rounded-xl p-4 mr-5 hover:border-purple-500">
                                    <div>
                                        <p>Device #{device.id}</p>
                                        <p>Description: {device.description}</p>
                                        <p>Max. Hourly Energy Consumption: {device.maxHourlyEnergyConsumption}</p>
                                        <p>Associated user: {device.user.username}</p>
                                    </div>
                            
                                    <div className="flex flex-grow">
                            
                                    </div>
                            
                                    <div className="flex flex-row items-center ml-44 space-x-4">
                                        <button className="button-user" onClick={() => {navigate('/admin/manage-devices/edit', {state:{currentUser, device}});}}>edit</button>
                                        <button className="button-user" onClick={() => {deleteDevice(device)}}>delete</button>
                                    </div>
                                </div>
                            )
                        }
                    </div>
                </div>
                

                <div>
                    <h5 className="font-poppins font-bold text-center">Add a new device</h5>
                    <h6 className="font-poppins text-center">* mandatory field</h6>

                    <div className="p-4 left-32 right-32 font-poppins">
                        <form className="flex flex-col items-center justify-center space-y-5">

                            <div className="flex flex-col">
                                <label>Description *</label>
                                <input placeholder=" short description" className="input-box" value={description} 
                                    onChange={e => setDescription(e.target.value)}/>
                            </div>
                            
                            <div className="flex flex-col">
                                <label>Address *</label>
                                <input placeholder=" city, street, no." className="input-box" value={address} 
                                    onChange={e => setAddress(e.target.value)}/>
                            </div>
                        
                            <div className="flex flex-col">
                                <label>Maximum hourly energy consumption *</label>
                                <input placeholder=" introduce a number" className="input-box" value={maxHourlyEnergyConsumption} 
                                    onChange={e => setMaxHourlyEnergyConsumption(e.target.value)}/>
                            </div>

                            <div className="flex flex-col">
                                <label>Associated user *</label>
                                <select className="form-select font-poppins select-box" id="users" onChange={(e) => { setSelectedUsernameForDevice(e.target.value) }}>
                                    {users.map((user) => <option value={user.username} key={user.id}>{user.username}</option>)}
                                </select>
                            </div>

                        </form>

                        <br></br>
                        <div className="flex flex-row justify-center items-center space-x-6">
                            <button className="register-button" onClick={addDevice}>Add</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>) : (<h></h>)
};

export default ManageDevicesPage;