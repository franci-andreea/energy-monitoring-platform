import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './AdminPage.css'

const AdminPage = () => {
    let navigate = useNavigate();

    const [currentUser, _] = useState(useLocation().state);

    useEffect(
        () => {
            checkUser();
        }, []
    )

    function checkUser() {
        if (currentUser == null || currentUser.role !== 'ADMIN') {
            navigate('/');
            return false;
        } else {
            return true;
        }
    }

    return (currentUser) ? (
        <div className="font-poppins">
            <div className="flex m-8 space-x-20">
                <div className="flex-none">
                    <button onClick={() => { navigate('/admin', { state: currentUser }); }}>Admin section - {currentUser.username}</button>
                </div>

                <div className="flex-grow" />

                <button className="logout-button" onClick={() => { navigate('/'); }}>↦</button>

            </div>

            <div className="flex flex-row m-9 space-x-24">
                <div className="w-14 h-14">
                    <button className="hover-button" onClick={() => { navigate('/admin/manage-users', { state: currentUser }); }}>USERS</button>
                </div>

                <div className="w-14 h-14">
                    <button className="hover-button" onClick={() => { navigate('/admin/manage-devices', { state: currentUser }); }}>DEVICES</button>
                </div>
            </div>

            <p className="text-4xl text-center text-purple-600 text-opacity-25">Welcome</p>
            <br></br><br></br><br></br>
            <p className="text-4xl text-center text-purple-600 text-opacity-25">Online Energy Monitoring Platform</p>
            <br></br><br></br><br></br>
            <p className="text-xl text-center text-purple-600 text-opacity-25">ADMIN SECTION</p>
        </div>) : (<h>loading...</h>)
    

};

export default AdminPage;