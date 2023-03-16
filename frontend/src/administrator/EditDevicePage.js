import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const EditDevicePage = () =>
{
    let navigate = useNavigate();

    const location = useLocation();
    const state = location.state;

    const [newDescription, setNewDescription] = useState("");
    const [newAddress, setNewAddress] = useState("");

    useEffect(
        () => {
            checkUser();
        }, []
    )

    function checkUser()
    {
        if(state === null || state.currentUser.role !== 'ADMIN')
        {
            navigate('/');
            return;
        }
    }

    function editDevice(device)
    {
        let editDeviceDTO = {
            newDescription: newDescription,
            newAddress: newAddress,
            deviceToEdit: device
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/edit-device', editDeviceDTO)
        .then(
            response => {
                navigate('/admin/manage-devices', {state:state.currentUser});
            }
        )
    }

    return (state) ? (
        <div className="edit-box">
            <div className="flex flex-row items-center space-x-4">
                <label>Current description</label>
                <input className="input-box cursor-not-allowed" value={state.device.description}></input>
            </div>
            <div className="flex flex-row items-center space-x-10">
                <label>New description</label>
                <input className="input-box" value={newDescription} onChange={e => setNewDescription(e.target.value)}></input>
            </div>

            <div className="flex flex-row items-center space-x-4">
                <label>Current address</label>
                <input className="input-box cursor-not-allowed" value={state.device.address}></input>
            </div>
            <div className="flex flex-row items-center space-x-10">
                <label>New address</label>
                <input className="input-box" value={newAddress} onChange={e => setNewAddress(e.target.value)}></input>
            </div>

            <div className="flex flex-row space-x-8">
                <button className="border-2 border-purple-600 rounded-xl p-2 hover:font-bold" onClick={() => {navigate('/admin/manage-devices', {state:state.currentUser});}}>cancel</button>
                <button className="border-2 border-purple-600 rounded-xl p-2 hover:font-bold" onClick={() => {editDevice(state.device)}}>edit</button>
            </div>
        </div>) : (<h></h>)
};

export default EditDevicePage;