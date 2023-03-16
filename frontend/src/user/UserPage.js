import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {BsFillChatFill} from "react-icons/bs"

import {over} from 'stompjs';
import SockJS from "sockjs-client";

var stompUser = null;

const UserPage = () => 
{
    let navigate = useNavigate();

    const [currentUser, setCurrentUser] = useState(useLocation().state);
    const [refreshKey, setRefreshKey] = useState(0);
    const [devices, setDevices] = useState([]);
    const [notificatioExists, setNotificationExists] = useState(false);
    const [notificationText, setNotificationText] = useState("");

    const onProcessMessage = (message) => {
        setNotificationText(message.body);
        setNotificationExists(true);
    }

    const onConnected = () => {

        devices.forEach(
            (d) => {
                stompUser.subscribe(`/device/${d.id}/notification`, onProcessMessage);
            }
        )
    }

    const onError = (error) => {
        console.log(error);
    }

    function initializeSock()
    {
        let Sock = new SockJS('http://localhost:3000/websocket');
        stompUser = over(Sock);
        stompUser.connect({}, onConnected, onError);
    }

    async function checkUser()
    {
        if(currentUser === null || currentUser.role !== 'USER')
        {
            navigate('/');
            return false;
        }
        
        return true;
    }

    async function getUserDevices()
    {
        return await axios.post(process.env.REACT_APP_SERVER_URL + '/get-user-devices', currentUser, {
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


    useEffect(
        () => {
            if(checkUser())
            {
                getUserDevices().then((val) => {setDevices(val)})
            }
        }, [refreshKey]
    )

    useEffect(
        () => {
            if(devices)
            {
                initializeSock();
            }
        }, [devices]
    )

    return (currentUser) ? (
        <div className="font-poppins">
            <div className="flex m-8 space-x-20">
                <div className="flex-none">
                    <button onClick={() => {navigate('/user', {state: currentUser});}}>User section - {currentUser.username}</button>
                </div>

                <div className="flex-grow" />

                <button className="logout-button" onClick={() => {navigate('/'); setCurrentUser(null)}}>↦</button>

            </div>

            <div className="flex flex-row m-9 space-x-24">
                <div className="w-96 h-14">
                    <button className="selected-section" onClick={() => {navigate('/user', {state: currentUser});}}>THESE ARE ALL YOUR REGISTERED DEVICES:</button>
                </div>
            </div>

            {(notificatioExists)? (<div>
                <div className="bg-orange-100 border-l-4 border-orange-500 text-orange-700 p-4 ml-5 mr-5" role="alert">
                    <p className="font-bold">Warning!</p>
                    <p>{notificationText}</p>
                </div>

                <br></br>
            </div>) : <></>}
            
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
                                <button className="button-user" onClick={() => {navigate('/user/devices', {state:{currentUser, device}});}}>view consumption</button>
                            </div>
                        </div>
                    )
                }
            </div>

            <button className="chat-icon" onClick={() => {navigate('/user/chat-page', {state:currentUser});}}>
                <BsFillChatFill size="28" />
            </button>
        </div>) : (<h></h>)
};

export default UserPage;