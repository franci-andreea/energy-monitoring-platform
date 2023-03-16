import axios from "axios";
import { useEffect, useState } from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";

const EditUserPage = () =>
{
    let navigate = useNavigate();

    const location = useLocation();
    const state = location.state;

    const [newUsername, setNewUsername] = useState("");

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

    function editUser(user)
    {
        let editUserDTO = {
            newUsername: newUsername,
            userToEdit: user
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/edit', editUserDTO)
        .then(
            response => {
                navigate('/admin/manage-users', {state:state.currentUser});
            }
        )
    }

    return (state) ? (
        <div className="edit-box">
            <div className="flex flex-row items-center space-x-4">
                <label>Current username</label>
                <input className="input-box cursor-not-allowed" value={state.user.username}></input>
            </div>
            <div className="flex flex-row items-center space-x-10">
                <label>New username</label>
                <input className="input-box" value={newUsername} onChange={e => setNewUsername(e.target.value)}></input>
            </div>

            <div className="flex flex-row space-x-8">
                <button className="border-2 border-purple-600 rounded-xl p-2 hover:font-bold" onClick={() => {navigate('/admin/manage-users', {state:state.currentUser});}}>cancel</button>
                <button className="border-2 border-purple-600 rounded-xl p-2 hover:font-bold" onClick={() => {editUser(state.user)}}>edit</button>
            </div>
        </div>) : (<h></h>)
    
};

export default EditUserPage;