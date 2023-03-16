import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import './AdminPage.css'
import '../chat/ChatPage.css'
import '../../src/register/RegisterPage.css'
import {BsFillChatFill} from "react-icons/bs"

const ManageUserPage = () =>
{
    let navigate = useNavigate();

    const [currentUser, _] = useState(useLocation().state);
    
    const [refreshKey, setRefreshKey] = useState(0);
    const [users, setUsers] = useState([]);

    const [nameIntroduced, setNameIntroduced] = useState("");
    const [ageIntroduced, setAgeIntroduced] = useState("");
    const [addressIntroduced, setAddressIntroduced] = useState("");
    const [usernameIntroduced, setUsernameIntroduced] = useState("");
    const [passwordIntroduced, setPasswordIntroduced] = useState("");

    useEffect(
        () => {
            if(checkUser())
            {
                getUsers().then((val) => {setUsers(val)});
            }
            
        }, [refreshKey]
    )

    function checkUser()
    {
        if(currentUser === null || currentUser.role !== 'ADMIN')
        {
            navigate('/');
            return false;
        }
        
        return true;
    }

    function getUsers()
    {
        return axios.get(process.env.REACT_APP_SERVER_URL + '/get-users', {
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
                console.log("get problems from backend - some error occurred");
                console.log(error);
                return [];
            }
        )
    }

    function changeRole(user)
    {
        axios.post(process.env.REACT_APP_SERVER_URL + '/change-role', user)
        .then(
            response => {
                if(response.status === 200)
                {
                    alert('Changed role for user ' + user.username + 'from USER to ADMIN');
                    setRefreshKey(refreshKey + 1);
                    return;
                }
            }
        )
        .catch(
            function(error)
            {
                console.log(user);
                console.log(error);
            }
        )
    }

    function deleteUser(userToDelete)
    {
        axios.post(process.env.REACT_APP_SERVER_URL + '/delete', userToDelete)
        .then(
            response => {
                if(response.status === 200)
                {
                    alert('user with username ' + userToDelete.username + ' successfully deleted from the db!');
                    setRefreshKey(refreshKey + 1);
                }
            }
        )
    }

    function registerUser()
    {
        let registerDTO = {
            name: nameIntroduced,
            address: addressIntroduced,
            age: ageIntroduced,
            username: usernameIntroduced,
            password: passwordIntroduced,
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/register', registerDTO)
        .then(
            response => 
            {
                console.log(response.data);
                alert("Successfully registered your account!");
                setRefreshKey(refreshKey + 1);
            }
        )
        .catch(
            function(error)
            {
                console.log("register user function - an error occurred");
                console.log(error);
            }
        )
    }

    return (currentUser) ? (
        <div className="font-poppins bg-slate-50 min-h-screen">
            <div className="flex p-7">
                <div className="flex-none">
                    <button onClick={() => {navigate('/admin', {state:currentUser});}}>Admin section - {currentUser.username}</button>
                </div>

                <div className="flex-grow">

                </div>

                <button className="logout-button" onClick={() => {navigate('/');}}>↦</button>

            </div>

            <div className="flex flex-row m-9 space-x-24">
                <div className="w-14 h-14">
                    <button className="selected-section" onClick={() => {navigate('/admin/manage-users', {state:currentUser});}}>USERS</button>
                </div>

                <div className="w-14 h-14">
                    <button className="hover-button1" onClick={() => {navigate('/admin/manage-devices', {state:currentUser});}}>DEVICES</button>
                </div>
            </div>

            <div className="grid grid-cols-2 divide-x">
                <div className="space-y-3">
                    {users.map(
                        (user) => 
                            <div className="flex flex-row ml-5 border-gray-200 border-2 rounded-xl p-4 mr-5 hover:border-purple-500">
                                <div>
                                    <p>User {user.username}</p>
                                    <p>role: {user.role}</p>
                                </div>
                        
                                <div className="flex flex-grow">
                        
                                </div>
                        
                                <div className="flex flex-row items-center ml-44 space-x-4">
                                    <button className="button-user" onClick={() => {navigate('/admin/manage-users/edit', {state:{currentUser, user}});}}>edit</button>
                                    <button className="button-user" onClick={() => {deleteUser(user)}}>delete</button>
                                    <button className="button-user" onClick={() => {changeRole(user)}}>make admin</button>
                                </div>
                            </div>
                        )
                    }
                </div>

                <div>
                    <h5 className="font-poppins text-center">Create a new account</h5>
                    <h6 className="font-poppins text-center">* mandatory field</h6>

                    <div className="p-4 left-32 right-32 font-poppins">
                        <form className="flex flex-col items-center justify-center space-y-5">

                            <div className="flex flex-col">
                                <label>Name *</label>
                                <input placeholder=" John Doe" className="input-box" value={nameIntroduced} 
                                    onChange={e => setNameIntroduced(e.target.value)}/>
                            </div>
                            
                            <div className="flex flex-col">
                                <label>Age *</label>
                                <input placeholder=" introduce a number" className="input-box" value={ageIntroduced} 
                                    onChange={e => setAgeIntroduced(e.target.value)}/>
                            </div>
                        
                            <div className="flex flex-col">
                                <label>Address *</label>
                                <input placeholder=" city, street, no." className="input-box" value={addressIntroduced} 
                                    onChange={e => setAddressIntroduced(e.target.value)}/>
                            </div>

                            <div className="flex flex-col">
                                <label>Username *</label>
                                <input placeholder=" e.g john15" className="input-box" value={usernameIntroduced} 
                                    onChange={e => setUsernameIntroduced(e.target.value)}/>
                            </div>

                            <div className="flex flex-col">
                                <label>Password *</label>
                                <input placeholder=" •••••••••" type="password" className="input-box" value={passwordIntroduced} 
                                    onChange={e => setPasswordIntroduced(e.target.value)}/>
                            </div>

                        </form>

                        <br></br>
                        <div className="flex flex-row justify-center items-center space-x-6">
                            <button className="register-button" onClick={registerUser}>Register</button>
                        </div>

                        <button className="chat-icon" onClick={() => {navigate('/admin/chat-page', {state:currentUser});}}>
                            <BsFillChatFill size="28" />
                        </button>
                    </div>
                </div>
            </div>  
        </div>) : (<h></h>)
};
export default ManageUserPage;